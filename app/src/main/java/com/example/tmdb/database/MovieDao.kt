package com.example.tmdb.database

import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Query


@Dao
interface MovieDao{

    @Insert
    fun insertMovie(movieEntitity: MovieEntity)

    @Delete
    fun deleteMovie(movieEntitity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies where movie_id= :movieId")
    fun getMovieById(movieId: String): MovieEntity
}