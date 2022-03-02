package com.example.tmdb.database

import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Query


@Dao
interface MovieDao{

    @Insert
   suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
   suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
   suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies where id= :movieId")
    suspend fun getMovieById(movieId: String): MovieEntity

    @Query("SELECT count(*) FROM movies where id = :movieId")
    suspend fun isMovieInTable(movieId: String): Int


}