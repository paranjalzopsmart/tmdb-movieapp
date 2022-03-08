package com.example.tmdb.repository

import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDao
import javax.inject.Inject


class Repository @Inject constructor(val MovieApiInterface: MovieApiInterface,
                   val movieDao: MovieDao
                  ){

     fun getMovieListquery(category: String) = MovieApiInterface.getMovieListquery(category)


}