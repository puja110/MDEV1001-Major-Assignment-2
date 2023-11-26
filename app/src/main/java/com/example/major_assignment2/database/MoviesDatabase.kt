package com.example.major_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*@Database(
    entities = [Movies::class],
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(MoviesConverters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabaseInstance(context: Context): MoviesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // The following query will add a new column called lastUpdate to the notes database
                database.execSQL("ALTER TABLE notes ADD COLUMN lastUpdate INTEGER NOT NULL DEFAULT 0")
            }
        }

        private fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "movies_database"
            )
                //.addMigrations(MIGRATION_1_2)
                .addCallback(PrepopulateMoviesCallback(context.applicationContext))
                .build()
        }
    }
}*/

//@Database(entities = [Movies::class], version = 1, exportSchema = true)
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

/*@Database(entities = [Movies::class], version = 1, exportSchema = true)
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
                        .addCallback(ImportMovies(context))
                        .build()
                    instance = DB_INSTANCE
                }
                return instance!!
            }
        }
    }
}*/

@Database(entities = [MoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao:MoviesDao

    companion object{
        @Volatile
        private var instance: MoviesDatabase? = null

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
