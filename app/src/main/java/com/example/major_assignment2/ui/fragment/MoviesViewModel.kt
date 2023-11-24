package com.example.major_assignment2.ui.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.database.MoviesDatabase

class MoviesViewModel(app: Application) : AndroidViewModel(app)  {
//    private val allMovies = repository.getAllMovies()

    val readAllMoviesData: LiveData<List<Movies>>
    private val repository: MoviesRepository

    init {
        val moviesDao = MoviesDatabase.getDatabaseInstance(app).moviesDao()
        repository = MoviesRepository(moviesDao)
        readAllMoviesData = repository.readAllMoviesData
    }

    /*fun addMovie(movie: Movies) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)
        }
    }*/

    /*fun insert(note: Movies) {
        repository.insert(note)
    }

    fun update(note: Movies) {
        repository.update(note)
    }

    fun delete(note: Movies) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }*/

   /* fun getAllMovies(): LiveData<List<Movies>> {
        return allMovies
    }*/


}