package com.example.major_assignment2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table")
    fun getMovies() : List<Movies>

    @Query("SELECT * FROM movies_table WHERE uid IN (:moviesIds)")
    fun loadAllMoviesByIds(moviesIds: IntArray): List<Movies>

    @Insert
    suspend fun insertMovie(movies: Movies)

    @Insert
    suspend  fun insertMoviesList(movies : List<Movies>)

    @Delete
    suspend fun delete(movies: Movies)
}