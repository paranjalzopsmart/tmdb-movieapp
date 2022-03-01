package com.example.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.Repository
import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dashboardViewModel (private val repository: Repository): ViewModel(){

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList

    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

    fun changeCategory(string: String){
        category.value = string
        getMovieListquery(string)
    }

    fun getMovieListquery(category: String){
        val response = repository.getMovieListquery(category)
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }


}