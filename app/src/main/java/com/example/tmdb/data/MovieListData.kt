package com.example.tmdb.data


class MovieListData (
    val page: Int,
    val results: List<MovieData>,
    val total_results: Int,
    val total_pages: Int
)