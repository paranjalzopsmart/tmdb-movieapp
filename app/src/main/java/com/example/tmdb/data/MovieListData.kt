package com.example.tmdb.data

import com.example.tmdb.database.MovieEntity


class MovieListData (
    val page: Int,
    val results: List<MovieEntity>,
    val total_results: Int,
    val total_pages: Int
)