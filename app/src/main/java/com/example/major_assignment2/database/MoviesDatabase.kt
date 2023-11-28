package com.example.major_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// defining entity class and database version
@Database(entities = [MoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    // defining movie dao class in the database
    abstract val moviesDao: MoviesDao

    companion object{
        @Volatile
        private var instance: MoviesDatabase? = null

        // creating database and instance of the movie database
        fun getDatabaseInstance(context: Context): MoviesDatabase?{
            if (instance == null){
                synchronized(MoviesDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        "movies_database"
                    )
                        .addCallback(ImportMoviesCallback(context))
                        .build()
                }
            }
            return instance
        }
    }
}
