package com.example.moviecatch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatch.R
import com.example.moviecatch.di.dao.GenreData
import com.example.moviecatch.models.Result
import java.util.*

class RecentMovieAdapter(private val isFirstScreen: Boolean = true) :
    RecyclerView.Adapter<RecentMovieAdapter.MovieHolder>() {

    var liveData: List<Result>? = null
    var genreList: List<GenreData>? = null

    fun setLists(liveData: List<Result>, genreList: List<GenreData>) {
        this.liveData = liveData
        this.genreList = genreList
        notifyDataSetChanged()
    }

    class MovieHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView)
        val txtReleasedDate = view.findViewById<TextView>(R.id.txtReleasedDate)
        val txtVoteAverage = view.findViewById<TextView>(R.id.txtVoteAverage)

        fun bind(data: Result, genreList: List<GenreData>) {

            txtTitle.text = data.title
            //txtGenre.text = "Genre 1, Genre 2, Genre 3"

            val language = Locale.getDefault().language

            var genres = ""
            for (id in data.genre_ids) {
                var result = genreList.find { x -> x.genre_id == id }

                when {
                    result != null -> {
                        genres += when (language) {
                            "tr" -> result!!.tr_name + ", "
                            else -> result!!.en_name + ", "
                        }
                    }
                }
            }
            genres = genres.substring(0, genres.length - 2)
            txtGenre.text = genres

            txtReleasedDate.text = data.release_date
            txtVoteAverage.text = "${data.vote_average} / 10"

            Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentMovieAdapter.MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMovieAdapter.MovieHolder, position: Int) {
        holder.bind(liveData!![position], genreList!!)
    }

    override fun getItemCount(): Int {

        return when {
            liveData == null -> 0
            isFirstScreen -> 10
            else -> liveData!!.size
        }
    }
}