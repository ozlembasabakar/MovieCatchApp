package com.example.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.di.retrofit.RetrofitRepository
import com.example.moviecatch.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val repository: RetrofitRepository) :
    ViewModel() {

    var popularMovieList: MutableLiveData<Movie>
    var recentMovieList: MutableLiveData<Movie>

    init {
        popularMovieList = MutableLiveData()
        recentMovieList = MutableLiveData()
    }

    fun getObserverLiveData(isPopular: Boolean): MutableLiveData<Movie> {
        if (isPopular)
            return popularMovieList
        else
            return recentMovieList
    }


    fun loadData(page: String, isPopular: Boolean) {
        if (isPopular)
            repository.getPopularMovies(page, popularMovieList)
        else
            repository.getRecentMovies(page, recentMovieList)
    }
}
