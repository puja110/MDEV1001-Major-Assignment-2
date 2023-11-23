package com.example.major_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1, exportSchema = true)
abstract class MoviesDatabase : RoomDatabase() {

    companion object {
        private var DB_INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            synchronized(this) {
                var instance = DB_INSTANCE
                if (instance == null) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        "Movies_DB"
                    )
                        .build()
                    instance = DB_INSTANCE
                }
                return instance!!
            }
        }
    }
}