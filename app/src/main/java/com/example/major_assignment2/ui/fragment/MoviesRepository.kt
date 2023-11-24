package com.example.major_assignment2.ui.fragment

import androidx.lifecycle.LiveData
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.database.MoviesDao

class MoviesRepository(private val moviesDao: MoviesDao) {
    val readAllMoviesData: LiveData<List<Movies>> = moviesDao.realAllMoviesData()

    suspend fun addMovie(movie: Movies) {
        moviesDao.addMovie(movie)
    }
}

/*
class MoviesRepository(application: Application) {

    private var moviesDao: MoviesDao
    private var allMovies: LiveData<List<Movies>>

    private val database = MoviesDatabase.getDatabaseInstance(application)

    init {
        moviesDao = database.moviesDao()
        allMovies = moviesDao.getAllMovies()
    }

    fun insert(movie: Movies) {
        subscribeOnBackground {
            moviesDao.insertMovie(movie)
        }
    }

    fun update(movie: Movies) {
        subscribeOnBackground {
            //moviesDao.updateMovie(movie)
        }
    }

    fun delete(movie: Movies) {
        subscribeOnBackground {
            moviesDao.deleteMovie(movie)
        }
    }

    fun deleteAllNotes() {
        subscribeOnBackground {
            //moviesDao.deleteAllMovies()
        }
    }

    fun getAllMovies(): LiveData<List<Movies>> {
        return allMovies
    }
}*/
