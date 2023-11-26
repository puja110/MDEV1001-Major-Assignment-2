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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: MoviesEntity)

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun realAllMoviesData(): LiveData<List<MoviesEntity>>

    @Update
    suspend fun updateMovie(movie: MoviesEntity)

    @Delete
    suspend fun deleteMovie(moviesEntity: MoviesEntity)

    /*@Query("SELECT * FROM movies_table ORDER BY id DESC")
    fun getMovies(): Flow<List<MoviesEntity>>*/
}