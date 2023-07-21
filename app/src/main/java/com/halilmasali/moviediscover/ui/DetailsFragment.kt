package com.halilmasali.moviediscover.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelCast
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.databinding.FragmentDetailsBinding
import com.halilmasali.moviediscover.ui.adapters.CustomItemAdapter
import com.halilmasali.moviediscover.viewModels.ItemsViewModel
import com.halilmasali.moviediscover.viewModels.SharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var adapter: CustomItemAdapter? = null
    private lateinit var data: ArrayList<ItemsViewModel>
    private lateinit var dataRepository: DataRepository
    private val sharedViewModel: SharedViewModel<Any> by activityViewModels()
    private var detailType: Any? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.recyclerViewDetailContent.layoutManager = LinearLayoutManager(requireContext())
        dataRepository = DataRepository(this, requireContext())
        data = ArrayList()
        for (i in 0..1){
            data.add(ItemsViewModel())
        }
        adapter = CustomItemAdapter(data)
        binding.recyclerViewDetailContent.adapter = adapter
        // Calculate the total height of all items in the RecyclerView
        val totalHeight = calculateTotalItemsHeight(binding.recyclerViewDetailContent)
        // Set the calculated height to the RecyclerView
        val params = binding.recyclerViewDetailContent.layoutParams
        params.height = totalHeight
        binding.recyclerViewDetailContent.layoutParams = params

        adapter?.setOnItemClickListener(object : CustomItemAdapter.OnItemClickListener {
            override fun onItemClick(data: Any) {
                if (data is CreditsModelCast){
                    Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT).show()
                } else{
                    sharedViewModel.saveDetailData(data)
                    findNavController().currentDestination?.getAction(R.id.action_detailsFragment_self)?.let {
                        findNavController().popBackStack(it.destinationId, false)
                    }
                }
            }
            override fun onRefreshClick() {
                if (detailType is SeriesModelResults)
                    getSeriesDetail((detailType as SeriesModelResults).id!!)
                else if (detailType is MovieModelResults)
                    getMovieDetail((detailType as MovieModelResults).id!!)
            }
        })

        sharedViewModel.detailData.observe(viewLifecycleOwner){ data ->
            when(data){
                is SeriesModelResults -> {
                    detailType = data
                    getSeriesDetail(data.id!!)
                    binding.textTitle.text = data.name
                    binding.textReleaseDate.text = data.firstAirDate
                    binding.textAvgVote.text = data.voteAverage.toString()
                    binding.textOverview.text = data.overview
                    Glide.with(this).load(
                        Constants.ImageBaseUrl + data.backdropPath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imageBackdrop)
                    Glide.with(this).load(
                        Constants.ImageBaseUrl + data.posterPath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imagePoster)
                }
                is MovieModelResults -> {
                    detailType = data
                    getMovieDetail(data.id!!)
                    binding.textTitle.text = data.title
                    binding.textReleaseDate.text = data.releaseDate
                    binding.textAvgVote.text = data.voteAverage.toString()
                    binding.textOverview.text = data.overview
                    Glide.with(this).load(
                        Constants.ImageBaseUrl + data.backdropPath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imageBackdrop)
                    Glide.with(this).load(
                        Constants.ImageBaseUrl + data.posterPath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imagePoster)
                }
            }
        }
        return binding.root
    }

    private fun getSeriesDetail(seriesId:Int){
        data.clear()
        dataRepository.getSeriesCast(seriesId).observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(1,"Cast", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getSeriesSimilar(seriesId).observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel(2,"Similar Series", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
    }

    private fun getMovieDetail(movieId:Int){
        data.clear()
        dataRepository.getMovieCast(movieId).observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(1,"Cast", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getMoviesSimilar(movieId).observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(2,"Similar Movies", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
    }

    private fun calculateTotalItemsHeight(recyclerView: RecyclerView): Int {
        val adapter = recyclerView.adapter
        var totalHeight = 0
        if (adapter != null) {
            for (i in 0 until adapter.itemCount) {
                val itemView = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i)).itemView
                itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(recyclerView.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                totalHeight += itemView.measuredHeight
            }
        }
        return totalHeight
    }
}