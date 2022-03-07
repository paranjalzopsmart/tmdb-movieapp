package com.example.tmdb.repository

import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl


class Repository (val MovieApiInterface: MovieApiInterface,
                  val databaseHelperImpl: DatabaseHelperImpl
                  ){

     fun getMovieListquery(category: String) = MovieApiInterface.getMovieListquery(category)


}