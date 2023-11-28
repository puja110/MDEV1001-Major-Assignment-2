package com.example.major_assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    // query function to add a new movie
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: MoviesEntity)

    // query function to fetch all the movie list from the movies_table
    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun getAllMoviesData(): LiveData<List<MoviesEntity>>

    // query function to update a movie
    @Update
    suspend fun updateMovie(movie: MoviesEntity)

    // query function to delete a movie
    @Delete
    suspend fun deleteMovie(moviesEntity: MoviesEntity)
}