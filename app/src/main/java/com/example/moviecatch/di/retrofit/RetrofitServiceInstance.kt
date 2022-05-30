package com.example.moviecatch.di.retrofit

import com.example.moviecatch.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=31cc460efadcf3de34f1b7ffcdb1ee97")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?api_key=31cc460efadcf3de34f1b7ffcdb1ee97")
    fun getRecentVideos(@Query("page") query: String): Call<Movie>

    @GET("3/genre/movie/list?api_key=31cc460efadcf3de34f1b7ffcdb1ee97")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/videos?/api_key=31cc460efadcf3de34f1b7ffcdb1ee97")
    fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>

    @GET("3/search/movie?/api_key=31cc460efadcf3de34f1b7ffcdb1ee97&language=en-US")
    fun getSuggestions(@Query("page") query: String): Call<Movie>
}