package com.example.tmdb.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.MovieListData
import com.example.tmdb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _page = MutableLiveData(1)

    private val _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>> = _movieList

    private val _favouriteMovies = MutableLiveData<List<MovieEntity>>()
    val favouriteMovies: LiveData<List<MovieEntity>> = _favouriteMovies

    private val _currentMovie = MutableLiveData("")
    private val _isFav = MutableLiveData(false)
    val isFav: LiveData<Boolean> = _isFav
    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData("popular")

    fun getNextPage() {
        _page.value = _page.value?.plus(1)
        getMovieListQuery()
    }

    fun changeCategory(string: String) {
        _page.value = 1
        _movieList.value = emptyList()
        category.value = string
        getMovieListQuery()
    }
    private fun getMovieListQuery() {
        val response = repository.getMovieListQuery(category = category.value!!, page = _page.value!!)
        response.enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                val newList = mutableListOf<MovieEntity>()
                newList.addAll(movieList.value ?: emptyList())
                newList.addAll(response.body()?.results ?: emptyList())
                _movieList.postValue(newList)
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    private suspend fun isMovieInTable(id: String): Boolean {
        val movie = repository.movieDao.isMovieInTable(id)

        if (movie > 0)
            return true
        return false
    }

    fun showFav() {
        // var favouriteMovies: List<MovieEntity>
        viewModelScope.launch {
            _favouriteMovies.value = repository.movieDao.getMovies()
        }
    }
    fun changeMovie(string: String) {
        viewModelScope.launch {
            _currentMovie.postValue(string)
            _isFav.postValue(isMovieInTable(_currentMovie.value!!))
        }
    }

    fun onButtonPress(movieClass: MovieEntity) {
        viewModelScope.launch {
            if (!isFav.value!!) {
                repository.movieDao.insertMovie(movieClass)
                _isFav.value = true
            } else {
                repository.movieDao.deleteMovie(movieClass)
                _isFav.value = false
            }
        }
    }
}
