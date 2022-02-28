package com.example.tmdb.data

import com.google.gson.annotations.SerializedName

class MovieListData (
    val page: Int,
    val results: List<MovieData>,
    val total_results: Int,
    val total_pages: Int
)