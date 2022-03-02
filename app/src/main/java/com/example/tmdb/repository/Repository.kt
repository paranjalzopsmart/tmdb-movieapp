package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDatabaseHelper

class Repository (val movieApiInterface: movieApiInterface,
                  val databaseHelperImpl: DatabaseHelperImpl
                  ){

     fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)


}