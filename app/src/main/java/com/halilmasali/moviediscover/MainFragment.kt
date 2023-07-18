package com.halilmasali.moviediscover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var adapter: CustomItemAdapter? = null
    private lateinit var data: ArrayList<ItemsViewModel>
    private lateinit var dataRepository: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(requireContext())
        dataRepository = DataRepository(this, requireContext())
        data = ArrayList()
        data.add(ItemsViewModel())
        adapter = CustomItemAdapter(data)
        binding.recyclerViewContent.adapter = adapter

        binding.toggleButton.check(binding.button1.id)
        binding.toggleButton.isSelectionRequired = true
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
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
    
    private fun getAllMoviesData() {
        data.clear()
        dataRepository.getMoviePopular().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel("Popular", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getMovieTopRated().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel("Top Rated", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getMovieUpcoming().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel("Upcoming", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }
        dataRepository.getMovieNowPlaying().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel("Now Playing", item.error, item.data))
            data.let { adapter!!.addList(it) }
        }        
    }
}