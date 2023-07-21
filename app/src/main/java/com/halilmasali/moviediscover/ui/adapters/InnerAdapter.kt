package com.halilmasali.moviediscover.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.creditsModel.CreditsModelCast
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.databinding.MoviePosterBinding

class InnerAdapter(private val items: List<Any>) : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    private lateinit var binding: MoviePosterBinding
    inner class ViewHolder(binding: MoviePosterBinding):RecyclerView.ViewHolder(binding.root) {
        val imageCard: ImageView = binding.imageCard
        val card: MaterialCardView = binding.card
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MoviePosterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val item = items[position]){
            is SeriesModelResults ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageCard)
                holder.card.setOnClickListener { onItemClickListener?.onItemClick(item) }
            }
            is MovieModelResults ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageCard)
                holder.card.setOnClickListener { onItemClickListener?.onItemClick(item) }
            }
            is CreditsModelCast ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.profilePath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageCard)
                holder.card.setOnClickListener { onItemClickListener?.onItemClick(item) }
            }
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