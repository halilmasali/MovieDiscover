package com.halilmasali.moviediscover

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults

class InnerAdapter(private val items: List<Any>) : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val context :Context = itemView.context
        val card: MaterialCardView = itemView.findViewById(R.id.card)
        val imageCard: ImageView = itemView.findViewById(R.id.imageCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_poster,parent,false)
        return ViewHolder(view)
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
                // TODO card click listen
                holder.card.setOnClickListener {  }
            }
            is MovieModelResults ->{
                Glide.with(holder.context).load(
                    Constants.ImageBaseUrl + item.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageCard)
                holder.card.setOnClickListener {  }
            }
        }
    }
}