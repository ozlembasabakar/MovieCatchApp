package com.example.moviecatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatch.R
import com.example.moviecatch.models.Result

class MovieAdapter(private val isFirstScreen: Boolean = true) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var liveData: List<Result>? = null

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MovieHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView)

        fun bind(data: Result) {

            txtTitle.text = data.title
            txtGenre.text = "Genre 1, Genre 2, Genre 3"

            Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(liveData!![position])
    }

    override fun getItemCount(): Int {

        if(liveData == null) {
            return 0
        } else if (isFirstScreen) {
            return 4
        } else {
            return liveData!!.size
        }


        /*return when {
            liveData != null -> 0
            isFirstScreen -> 4
            else -> liveData!!.size
        }*/
    }

}