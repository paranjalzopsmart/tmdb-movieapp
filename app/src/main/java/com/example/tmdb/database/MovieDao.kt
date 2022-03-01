package com.example.tmdb.database

import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Query
import com.example.tmdb.data.MovieData


@Dao
interface MovieDao{

    @Insert
   suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
   suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
   suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies where movie_id= :movieId")
    suspend fun getMovieById(movieId: String): MovieEntity
}