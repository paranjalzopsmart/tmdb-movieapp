package com.example.tmdb.database

import com.example.tmdb.database.MovieEntity

interface MovieDatabaseHelper {
    suspend fun getMovie(movieId: String): MovieEntity
    suspend fun getMovies(): List<MovieEntity>
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovie(movie: MovieEntity)
    suspend fun isMovieInTable(movieId: String): Int


    }