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

class RecentMovieAdapter(private val isFirstScreen: Boolean = true) :
    RecyclerView.Adapter<RecentMovieAdapter.MovieHolder>() {

    var liveData: List<Result>? = null

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MovieHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView)
        val txtReleasedDate = view.findViewById<TextView>(R.id.txtReleasedDate)
        val txtVoteAverage = view.findViewById<TextView>(R.id.txtVoteAverage)

        fun bind(data: Result) {

            txtTitle.text = data.title
            txtGenre.text = "Genre 1, Genre 2, Genre 3"
            txtReleasedDate.text = data.relased_date
            txtVoteAverage.text = data.vote_average.toString() + " / 10"

            Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMovieAdapter.MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMovieAdapter.MovieHolder, position: Int) {
        holder.bind(liveData!![position])
    }

    override fun getItemCount(): Int {

        return when {
            liveData == null -> 0
            isFirstScreen -> 4
            else -> liveData!!.size
        }
    }
}