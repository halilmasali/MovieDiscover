package com.halilmasali.moviediscover

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomItemAdapter : RecyclerView.Adapter<CustomItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var error: TextView
        var buttonRefresh: Button
        var recyclerViewCustom: RecyclerView

        init {
            title = itemView.findViewById(R.id.textTitle)
            error = itemView.findViewById(R.id.textError)
            buttonRefresh = itemView.findViewById(R.id.buttonRefresh)
            recyclerViewCustom = itemView.findViewById(R.id.recyclerViewCustom)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

