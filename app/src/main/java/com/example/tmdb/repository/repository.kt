package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface

class repository (private val movieApiInterface: movieApiInterface){

     fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)
     /*
      fun getMovieList(language: String, page: Int) = movieApiInterface.getMovieList(language, page)
      fun getTopRatedList(language: String, page: Int) = movieApiInterface.getTopRatedList(language, page)
      fun getUpcomingMovies(language: String, page: Int) = movieApiInterface.getUpcomingMovies(language, page)
      */
}