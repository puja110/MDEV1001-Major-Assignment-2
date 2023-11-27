package com.example.major_assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.major_assignment2.database.MoviesDatabase

class MyViewModelFactory constructor(private val moviesDatabase: MoviesDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(moviesDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}