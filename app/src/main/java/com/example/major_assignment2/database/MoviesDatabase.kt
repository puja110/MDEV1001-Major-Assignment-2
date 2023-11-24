package com.example.major_assignment2.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1, exportSchema = true)
//class MoviesDatabase : Application() {
//    companion object {
//        lateinit var database: MoviesDatabase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        database = Room.databaseBuilder(
//            applicationContext,
//            MoviesDatabase::class.java,
//            "movies_database"
//        ).build()
//    }
//}
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        private var DB_INSTANCE: MoviesDatabase? = null

        fun getDatabaseInstance(context: Context): MoviesDatabase {
            synchronized(this) {
                var instance = DB_INSTANCE
                if (instance == null) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context,
                        MoviesDatabase::class.java,
                        "Movies_DB"
                    )
                        .addCallback(PrepopulateMoviesCallback(context))
                        .build()
                    instance = DB_INSTANCE
                }
                return instance!!
            }
        }
    }
}