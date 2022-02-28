package com.example.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.repository.repository
import java.lang.IllegalArgumentException

class MyViewModelFactory constructor(private val repository: repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(dashboardViewModel::class.java)) {
            dashboardViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("viewmodel not found")
        }
    }
}