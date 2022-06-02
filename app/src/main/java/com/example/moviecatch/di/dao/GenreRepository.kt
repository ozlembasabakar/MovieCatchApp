package com.example.moviecatch.di.dao

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GenreRepository @Inject constructor(private val genreDao: GenreDao) {

    val readAllData: LiveData<List<GenreData>> = genreDao.readAllData()

    fun addGenreData(genreData: GenreData) = genreDao.addGenre(genreData)

    fun addAllGenres(genreList: List<GenreData>) = genreDao.addAllGenres(genreList)
}