package com.halilmasali.moviediscover.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.dataRepository.DataRepository
import com.halilmasali.moviediscover.databinding.FragmentMainBinding
import com.halilmasali.moviediscover.ui.adapters.CustomItemAdapter
import com.halilmasali.moviediscover.viewModels.ItemsViewModel
import com.halilmasali.moviediscover.viewModels.SharedViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var adapter: CustomItemAdapter? = null
    private lateinit var data: ArrayList<ItemsViewModel>
    private lateinit var dataRepository: DataRepository
    private lateinit var shimmer: ShimmerFrameLayout
    private val sharedViewModel: SharedViewModel<Any> by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(requireContext())
        dataRepository = DataRepository(this, requireContext())
        data = ArrayList()
        for (i in 0..3){
            data.add(ItemsViewModel())
        }
        adapter = CustomItemAdapter(data)
        binding.recyclerViewContent.adapter = adapter
        // Shimmer effect for loading
        shimmer = binding.shimmerLayout
        adapter?.setOnItemClickListener(object : CustomItemAdapter.OnItemClickListener {
            override fun onItemClick(data: Any) {
                sharedViewModel.saveDetailData(data)
                findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
            }

            override fun onRefreshClick() {
                if (binding.toggleButton.checkedButtonId == binding.button1.id)
                    getAllMoviesData()
                else if (binding.toggleButton.checkedButtonId == binding.button2.id)
                    getAllSeriesData()
            }
        })
        getAllMoviesData() // First load movie data
        binding.toggleButton.check(binding.button1.id)
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
        var count = 0
        data.clear()
        shimmer.startShimmer()
        binding.recyclerViewContent.visibility = View.GONE
        shimmer.visibility = View.VISIBLE
        dataRepository.getSeriesAiringToday().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(4,"Airing Today", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getSeriesTopRated().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(1,"Top Rated", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getSeriesPopular().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(2,"Popular", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getSeriesOnTheAir().observe(viewLifecycleOwner) { item ->
            data.add(ItemsViewModel(3,"On The Air", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
    }

    private fun getAllMoviesData() {
        var count = 0
        data.clear()
        shimmer.startShimmer()
        binding.recyclerViewContent.visibility = View.GONE
        shimmer.visibility = View.VISIBLE
        dataRepository.getMoviePopular().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel(2,"Popular", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getMovieTopRated().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel(1,"Top Rated", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getMovieUpcoming().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel(4,"Upcoming", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
        dataRepository.getMovieNowPlaying().observe(viewLifecycleOwner) { item->
            data.add(ItemsViewModel(3,"Now Playing", item.error, item.data))
            data.let { adapter!!.addList(it) }
            setShimmerVisibility(++count)
        }
    }

    private fun setShimmerVisibility(count: Int){
        if (count == 4){
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            binding.recyclerViewContent.visibility = View.VISIBLE
        }
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            binding.recyclerViewContent.visibility = View.VISIBLE
        }, 30000)
    }
}