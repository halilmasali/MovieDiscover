package com.halilmasali.moviediscover.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.halilmasali.moviediscover.R
import com.halilmasali.moviediscover.dataRepository.apiRepository.ExceptionHandler
import com.halilmasali.moviediscover.dataRepository.apiRepository.ErrorType
import com.halilmasali.moviediscover.databinding.CustomItemBinding
import com.halilmasali.moviediscover.viewModels.ItemsViewModel

class CustomItemAdapter(private var items: ArrayList<ItemsViewModel>) :
    RecyclerView.Adapter<CustomItemAdapter.ViewHolder>() {

    private lateinit var binding: CustomItemBinding

    inner class ViewHolder(binding: CustomItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.textTitle
        val error: TextView = binding.textError
        val buttonRefresh: Button = binding.buttonRefresh
        val recyclerViewInner: RecyclerView = binding.recyclerViewInner
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CustomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        if (item.error != null) {
            when(ExceptionHandler.handleError(item.error)){
                ErrorType.NO_INTERNET_CONNECTION -> {
                    holder.error.text = holder.context.getString(R.string.check_internet_connection)
                    holder.error.visibility = View.VISIBLE
                    holder.buttonRefresh.visibility = View.VISIBLE
                }
                ErrorType.API_ERROR -> {
                    holder.error.text = holder.context.getString(R.string.api_error_message)
                    holder.error.visibility = View.VISIBLE
                    holder.buttonRefresh.visibility = View.INVISIBLE
                }
                ErrorType.UNKNOWN_ERROR -> {
                    holder.error.text = holder.context.getString(R.string.unknown_error_message)
                    holder.error.visibility = View.VISIBLE
                    holder.buttonRefresh.visibility = View.INVISIBLE
                }
                else -> {
                    holder.error.visibility = View.INVISIBLE
                    holder.buttonRefresh.visibility = View.INVISIBLE
                }
            }
            holder.buttonRefresh.setOnClickListener { onItemClickListener?.onRefreshClick() }
        }
        val recyclerViewInner = holder.recyclerViewInner
        recyclerViewInner.layoutManager = LinearLayoutManager(
            holder.context, LinearLayoutManager.HORIZONTAL, false
        )
        val innerAdapter = item.data?.let { InnerAdapter(it) }
        recyclerViewInner.adapter = innerAdapter

        innerAdapter?.setOnItemClickListener(object : InnerAdapter.OnItemClickListener {
            override fun onItemClick(data: Any) {
                onItemClickListener?.onItemClick(data)
            }
        })
    }

    fun addList(addList: ArrayList<ItemsViewModel>) {
        items = addList
        notifyDataSetChanged()
    }
    interface OnItemClickListener {
        fun onItemClick(data: Any)
        fun onRefreshClick()
    }
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}

