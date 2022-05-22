package com.example.moviecatch.di.retrofit

import com.example.moviecatch.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=a7ed6cf184a83cd1e1ee9f3b1d76e8e7")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?/api_key=a7ed6cf184a83cd1e1ee9f3b1d76e8e7")
    fun getRecentVideos(@Query("page") query: String): Call<Movie>

    @GET("3/genre/movie/list?api_key=a7ed6cf184a83cd1e1ee9f3b1d76e8e7")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/videos?/api_key=a7ed6cf184a83cd1e1ee9f3b1d76e8e7")
    fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>

    @GET("3/search/movie?/api_key=a7ed6cf184a83cd1e1ee9f3b1d76e8e7e&language=en-US")
    fun getSuggestions(@Query("page") query: String): Call<Movie>
}