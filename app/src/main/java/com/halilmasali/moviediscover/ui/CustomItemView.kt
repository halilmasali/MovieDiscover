package com.halilmasali.moviediscover.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.dataRepository.DataResult
import com.halilmasali.moviediscover.dataRepository.apiRepository.error.ErrorHelper
import com.halilmasali.moviediscover.dataRepository.apiRepository.error.ExceptionHandler
import com.halilmasali.moviediscover.databinding.CustomItemViewBinding
import com.halilmasali.moviediscover.ui.adapters.InnerAdapter

class CustomItemView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // Add a lifecycle observer for this view
    private val binding: CustomItemViewBinding
    private var shimmer: ShimmerFrameLayout
    init {
        binding = CustomItemViewBinding.inflate(LayoutInflater.from(context), this, true)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomItemView)
        binding.textTitle.text = attributes.getString(R.styleable.CustomItemView_titleText)
        binding.textError.text = attributes.getString(R.styleable.CustomItemView_errorText)
        binding.buttonRefresh.text = attributes.getString(R.styleable.CustomItemView_refreshText)
        attributes.recycle()
        // Shimmer effect for loading
        shimmer = binding.shimmerLayout
        setShimmerVisibility(visible = true)
    }

    fun setTitleText(text: String) {
        binding.textTitle.text = text
    }

    fun <T : Any> setData(dataGetter: () -> LiveData<DataResult<ArrayList<T>>>) {
        dataGetter().observe(context as LifecycleOwner) { item ->
            setShimmerVisibility(visible = false)
            val error = ExceptionHandler.handleError(item.error)
            binding.textError.text = ErrorHelper.getErrorMessage(error, context)
            binding.textError.visibility = ErrorHelper.getErrorTextVisibility(error)
            binding.buttonRefresh.visibility = ErrorHelper.getErrorButtonVisibility(error)
            binding.buttonRefresh.setOnClickListener {
                setShimmerVisibility(visible = true)
                setData(dataGetter)
            }
            binding.recyclerViewInner.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            if (item.data.isNullOrEmpty() && item.error == null) {
                binding.textError.text = context.getString(R.string.no_data)
                binding.textError.visibility = View.VISIBLE
            } else {
                val innerAdapter = item.data?.let { InnerAdapter(it) }
                binding.recyclerViewInner.adapter = innerAdapter
                innerAdapter?.setOnItemClickListener(object : InnerAdapter.OnItemClickListener {
                    override fun onItemClick(data: Any) {
                        onItemClickListener?.onItemClick(data)
                    }
                })
            }
        }
    }

    private fun setShimmerVisibility(visible:Boolean){
        if (visible){
            shimmer.startShimmer()
            shimmer.visibility = View.VISIBLE
            binding.textTitle.visibility = View.INVISIBLE
            binding.recyclerViewInner.visibility = View.INVISIBLE
            binding.textError.visibility = View.INVISIBLE
            binding.buttonRefresh.visibility = View.INVISIBLE
        }else{
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            binding.textTitle.visibility = View.VISIBLE
            binding.recyclerViewInner.visibility = View.VISIBLE
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: Any)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}