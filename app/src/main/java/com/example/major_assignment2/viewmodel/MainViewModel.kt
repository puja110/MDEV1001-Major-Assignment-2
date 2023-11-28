package com.example.major_assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.database.MoviesDatabase
import kotlinx.coroutines.launch

class MainViewModel(private val noteDatabase: MoviesDatabase) : ViewModel() {

    val movies = noteDatabase.moviesDao.getAllMoviesData()

    fun addMovie(noteEntity: MoviesEntity){
        viewModelScope.launch{
            noteDatabase.moviesDao.addMovie(noteEntity)
        }
    }

    suspend fun updateMovie(movieEntity: MoviesEntity){
        viewModelScope.launch{
            noteDatabase.moviesDao.updateMovie(movieEntity)
        }
    }

    suspend fun deleteMovie(movieEntity: MoviesEntity){
        viewModelScope.launch{
            noteDatabase.moviesDao.deleteMovie(movieEntity)
        }
    }
}