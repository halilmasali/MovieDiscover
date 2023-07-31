package com.halilmasali.moviediscover.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.databinding.FragmentMainBinding
import com.halilmasali.moviediscover.viewModels.SharedViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dataRepository: DataRepository
    private val sharedViewModel: SharedViewModel<Any> by activityViewModels()
    private val mainActivity: MainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        dataRepository = DataRepository(this, requireContext())
        getAllMoviesData() // First load movie data
        setClickListeners()
        binding.toggleButton.check(binding.button1.id)
        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked){
                when(checkedId){
                    binding.button1.id -> {
                        getAllMoviesData()
                    }
                    binding.button2.id -> {
                        getAllSeriesData()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
        if (!mainActivity.isChangingConfigurations){
            mainActivity.finish()
        }
    }

    private fun getAllSeriesData() {
        binding.customItemView1.setData { dataRepository.getSeriesTopRated() }
        binding.customItemView1.setTitleText("Top Rated")

        binding.customItemView2.setData { dataRepository.getSeriesPopular() }
        binding.customItemView2.setTitleText("Popular")

        binding.customItemView3.setData { dataRepository.getSeriesOnTheAir() }
        binding.customItemView3.setTitleText("On The Air")

        binding.customItemView4.setData { dataRepository.getSeriesAiringToday() }
        binding.customItemView4.setTitleText("Airing Today")
    }

    private fun getAllMoviesData() {
        binding.customItemView1.setData { dataRepository.getMovieTopRated() }
        binding.customItemView1.setTitleText("Top Rated")

        binding.customItemView2.setData { dataRepository.getMoviePopular() }
        binding.customItemView2.setTitleText("Popular")

        binding.customItemView3.setData { dataRepository.getMovieNowPlaying() }
        binding.customItemView3.setTitleText("Now Playing")

        binding.customItemView4.setData { dataRepository.getMovieUpcoming() }
        binding.customItemView4.setTitleText("Upcoming")
    }

    private fun setClickListeners() {
        binding.customItemView1.setOnItemClickListener(object:CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }
        })
        binding.customItemView2.setOnItemClickListener(object:CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }
        })
        binding.customItemView3.setOnItemClickListener(object:CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }
        })
        binding.customItemView4.setOnItemClickListener(object:CustomItemView.OnItemClickListener {
            override fun onItemClick(data: Any) { onItemClicked(data) }
        })
    }

    private fun onItemClicked(data: Any) {
        sharedViewModel.addDetailData(data)
        mainActivity.customFragmentManager.addFragment(DetailsFragment())
    }
}