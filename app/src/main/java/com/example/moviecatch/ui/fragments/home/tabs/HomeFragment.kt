package com.example.moviecatch.ui.fragments.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatch.adapter.MovieAdapter
import com.example.moviecatch.adapter.RecentMovieAdapter
import com.example.moviecatch.databinding.FragmentHomeBinding
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerViews()

        viewModel.getObserverLiveData(isPopular = true).observe(viewLifecycleOwner
        ) { t ->
            if (t != null)
                movieAdapter.setList(t.results)
        }

        viewModel.getObserverLiveData(false).observe(viewLifecycleOwner
        ) { t ->
            if (t != null)
                recentMovieAdapter.setList(t.results)
        }

        fetchMovies()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initRecyclerViews() {

        val lmHorizontal =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val lmVertical =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.layoutManager = lmHorizontal
        binding.recentRecyclerView.layoutManager = lmVertical

        movieAdapter = MovieAdapter()
        recentMovieAdapter = RecentMovieAdapter()

        binding.recyclerView.adapter = movieAdapter
        binding.recentRecyclerView.adapter = recentMovieAdapter

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