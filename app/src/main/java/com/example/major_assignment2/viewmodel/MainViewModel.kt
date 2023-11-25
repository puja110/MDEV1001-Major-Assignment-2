package com.example.major_assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.database.MoviesDatabase
import kotlinx.coroutines.launch

class MainViewModel(private val noteDatabase: MoviesDatabase) : ViewModel() {

    val movies = noteDatabase.moviesDao.realAllMoviesData()

    fun insertNote(noteEntity: Movies){
        viewModelScope.launch{
            noteDatabase.moviesDao.addMovie(noteEntity)
        }
    }
}