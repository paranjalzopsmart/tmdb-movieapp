package com.example.tmdb.repository

import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.models.MovieListData
import com.example.tmdb.models.databse.MovieDao
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(
    private val MovieApiInterface: MovieApiInterface,
    val movieDao: MovieDao
) {

    fun getMovieListQuery(category: String, page: Int): Call<MovieListData> {
        return MovieApiInterface.getMovieListQuery(category, page)
    }
}
