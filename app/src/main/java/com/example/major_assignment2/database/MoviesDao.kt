package com.example.major_assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table")
    fun getMoviesData() : Flow<List<Movies>>

    @Query("SELECT * FROM movies_table ORDER BY id DESC")
    fun getMovies(): Flow<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movies)

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun realAllMoviesData(): LiveData<List<Movies>>

    @Query("SELECT * FROM movies_table")
    fun getAllMovies() : LiveData<List<Movies>>

//    @Query("SELECT * FROM movies_table WHERE uid IN (:moviesIds)")
//    fun loadAllMoviesByIds(moviesIds: IntArray): List<Movies>

    @Insert
    fun insertMovie(movies: Movies)

    @Insert
    suspend  fun insertMoviesList(movies : List<Movies>)

    @Delete
    fun deleteMovie(movies: Movies)
}