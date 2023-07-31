package com.halilmasali.moviediscover.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.card.MaterialCardView
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.R
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
        private val shimmer = Shimmer.ColorHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setBaseColor(ContextCompat.getColor(context, R.color.gray)) //the base color of the shimmer
            .setDuration(1000) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

        // This is the placeholder for the imageView
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
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
                    .placeholder(holder.shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(holder.imageCard)
                holder.card.setOnClickListener { onItemClickListener?.onItemClick(item) }
            }
            is MovieModelResults ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.posterPath)
                    .placeholder(holder.shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(holder.imageCard)
                holder.card.setOnClickListener { onItemClickListener?.onItemClick(item) }
            }
            is CreditsModelCast ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.profilePath)
                    .placeholder(holder.shimmerDrawable)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
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