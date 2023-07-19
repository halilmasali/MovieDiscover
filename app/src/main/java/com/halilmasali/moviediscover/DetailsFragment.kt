package com.halilmasali.moviediscover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var adapter: CustomItemAdapter? = null
    private lateinit var data: ArrayList<ItemsViewModel>
    private lateinit var dataRepository: DataRepository
    private val sharedViewModel: SharedViewModel<Any> by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.recyclerViewDetailContent.layoutManager = LinearLayoutManager(requireContext())
        dataRepository = DataRepository(this, requireContext())
        data = ArrayList()
        data.add(ItemsViewModel())
        adapter = CustomItemAdapter(data)
        binding.recyclerViewDetailContent.adapter = adapter

        adapter?.setOnItemClickListener(object : CustomItemAdapter.OnItemClickListener {
            override fun onItemClick(data: Any) {
                sharedViewModel.saveDetailData(data)
                findNavController().navigate(R.id.action_detailsFragment_self)
            }
        })

        getAllSeriesData()



        sharedViewModel.detailData.observe(viewLifecycleOwner){ data ->
            when(data){
                is SeriesModelResults -> {
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
    // FIXME it's for test delete it
    private fun getAllSeriesData() {
        data.clear()
        dataRepository.getSeriesAiringToday().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel("Airing Today", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getSeriesTopRated().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel("Top Rated", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getSeriesPopular().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel("Popular", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getSeriesOnTheAir().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel("On The Air", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
    }

}