package com.example.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Repository
import com.example.tmdb.data.MovieListData
import com.example.tmdb.database.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(val repository: Repository): ViewModel(){

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList
    private val _favouriteMovies = MutableLiveData<List<MovieEntity>>()
    val favouriteMovies: LiveData<List<MovieEntity>> = _favouriteMovies
    private val _currentMovie  = MutableLiveData<String>("")
    private val _isFav = MutableLiveData<Boolean>(false)
    val isFav: LiveData<Boolean> = _isFav
    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

    fun changeCategory(string: String){
        category.value = string
        getMovieListquery (string)
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
    suspend fun isMovieInTable(id: String): Boolean{
        val movie = repository.movieDao.isMovieInTable(id)

        if (movie > 0)
            return true
        return false
    }



    fun showFav(){
        //var favouriteMovies: List<MovieEntity>
        viewModelScope.launch {
            _favouriteMovies.value = repository.movieDao.getMovies()
        }
    }
    fun changeMovie(string: String){
        viewModelScope.launch {
            _currentMovie.postValue(string)
            _isFav.postValue(isMovieInTable(_currentMovie.value!!))
        }
    }

    fun onButtonPress(movieclass: MovieEntity) {
        viewModelScope.launch {
            if (!isFav.value!!) {
                repository.movieDao.insertMovie(movieclass)
                _isFav.value = true
            } else {
                repository.movieDao.deleteMovie(movieclass)
                _isFav.value = false
            }
        }
    }
}