package com.example.moviecatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatch.adapter.MovieAdapter
import com.example.moviecatch.adapter.RecentMovieAdapter
import com.example.moviecatch.models.Movie
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerViews()

        viewModel.getObserverLiveData(true).observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if (t != null)
                    movieAdapter.setList(t.results)
            }
        })

        viewModel.getObserverLiveData(false).observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if (t != null)
                    recentMovieAdapter.setList(t.results)

            }
        })

        fetchMovies()

    }

    fun initRecyclerViews() {

        val lmHorizontal =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val lmVertical =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recentRecyclerView = findViewById<RecyclerView>(R.id.recentRecyclerView)

        recyclerView.layoutManager = lmHorizontal
        recentRecyclerView.layoutManager = lmVertical

        movieAdapter = MovieAdapter()
        recentMovieAdapter = RecentMovieAdapter()

        recyclerView.adapter = movieAdapter
        recentRecyclerView.adapter = recentMovieAdapter

    }

    fun fetchMovies() {

        CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
                viewModel.loadData("1", true)
            }

            val job2: Deferred<Unit> = async {
                viewModel.loadData("1", false)
            }

            job1.await()
            job2.await()

        }
    }
}