package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDatabaseHelper

class Repository (private val movieApiInterface: movieApiInterface,
                  private val databaseHelperImpl: DatabaseHelperImpl
                  ){

     fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)


}