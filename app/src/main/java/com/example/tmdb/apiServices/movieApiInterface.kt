package com.example.tmdb.apiServices


import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface movieApiInterface {


    @GET("movie/{category}?api_key=286fe5ed7f42deec75f227d9df455b36&language=en-US")
    fun getMovieListquery(
        @Path("category")
        category: String
    ): Call<MovieListData>



    companion object{
        var retroService: movieApiInterface? = null

        fun getInstance(): movieApiInterface{
            if (retroService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retroService = retrofit.create(movieApiInterface::class.java)
            }
            return retroService!!
        }
    }
}