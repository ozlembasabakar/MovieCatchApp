package com.example.moviecatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatch.R
import com.example.moviecatch.models.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    var liveData: List<Result>? = null

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!![position])
    }

    override fun getItemCount(): Int {
        return if (liveData == null) 0 else liveData!!.size
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txtTitle = view.findViewById<TextView>(R.id.title)

        fun bind(data: Result) {
            txtTitle.text = data.title

        }

    }
}