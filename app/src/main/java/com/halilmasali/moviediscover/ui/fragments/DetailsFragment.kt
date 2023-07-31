package com.halilmasali.moviediscover.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelCast
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.databinding.FragmentDetailsBinding
import com.halilmasali.moviediscover.ui.CustomItemView
import com.halilmasali.moviediscover.viewModels.SharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dataRepository: DataRepository
    private val sharedViewModel: SharedViewModel<Any> by activityViewModels()
    private var isLastItem = false
    private lateinit var shimmer : Shimmer

    // This is the placeholder for the imageView
    private lateinit var shimmerDrawable :ShimmerDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        dataRepository = DataRepository(this, requireContext())
        setClickListeners() // Set click listeners for item views
        // Get fragment content data
        sharedViewModel.detailDataList.observe(viewLifecycleOwner) { dataList ->
            if (dataList.isNotEmpty()){
                val lastData = dataList.last()
                if (lastData != null) {
                    updateFragmentContent(lastData)
                    // Check if the last item is added to the list
                    isLastItem = dataList.size == 1
                }
            }
        }
        // Shimmer effect for movie card
        shimmer = Shimmer.ColorHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setBaseColor(ContextCompat.getColor(requireContext(), R.color.gray)) //the base color of the shimmer
            .setDuration(1000) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()
        // This is the placeholder for the imageView
        shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Handle back button press for fragment refresh data
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            sharedViewModel.removeLastDetailData()
            if (isLastItem) {
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }
    }

    private fun getSeriesDetail(seriesId:Int){
        binding.itemViewCast.setData { dataRepository.getSeriesCast(seriesId) }
        binding.itemViewSimilar.setData { dataRepository.getSeriesSimilar(seriesId) }
    }

    private fun getMovieDetail(movieId:Int) {
        binding.itemViewCast.setData { dataRepository.getMovieCast(movieId) }
        binding.itemViewSimilar.setData { dataRepository.getMoviesSimilar(movieId) }
    }

    private fun updateFragmentContent(data:Any){
        // Scroll to top
        binding.scrollView.post { binding.scrollView.scrollTo(0,0) }
        when(data){
            is SeriesModelResults -> {
                getSeriesDetail(data.id!!)
                binding.textTitle.text = data.name
                binding.textReleaseDate.text = data.firstAirDate
                binding.textAvgVote.text = data.voteAverage.toString()
                binding.textOverview.text = data.overview
                Glide.with(this).load(
                    Constants.ImageBaseUrl + data.backdropPath)
                    .placeholder(shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imageBackdrop)
                Glide.with(this).load(
                    Constants.ImageBaseUrl + data.posterPath)
                    .placeholder(shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imagePoster)
            }
            is MovieModelResults -> {
                getMovieDetail(data.id!!)
                binding.textTitle.text = data.title
                binding.textReleaseDate.text = data.releaseDate
                binding.textAvgVote.text = data.voteAverage.toString()
                binding.textOverview.text = data.overview
                Glide.with(this).load(
                    Constants.ImageBaseUrl + data.backdropPath)
                    .placeholder(shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imageBackdrop)
                Glide.with(this).load(
                    Constants.ImageBaseUrl + data.posterPath)
                    .placeholder(shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imagePoster)
            }
        }
    }

    private fun setClickListeners() {
        binding.itemViewCast.setOnItemClickListener(object : CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }})
        binding.itemViewSimilar.setOnItemClickListener(object : CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }})
    }

    private fun onItemClicked(data: Any) {
        if (data is CreditsModelCast){
            Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT).show()
        } else{
            sharedViewModel.addDetailData(data)
        }
    }
}