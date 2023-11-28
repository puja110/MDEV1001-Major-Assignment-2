package com.example.major_assignment2.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.major_assignment2.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class ImportMoviesCallback(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithImportingMovies(context)
        }
    }

    // importing movie JSON file in the database
    private suspend fun fillWithImportingMovies(context: Context){
        //obtaining instance of data access object
        val dao = MoviesDatabase.getDatabaseInstance(context)?.moviesDao

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val movies = loadJSONArray(context)
            if (movies != null){
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until movies.length()){
                    //variable to obtain the JSON object
                    val item = movies.getJSONObject(i)
                    //Using the JSON object to assign data
                    val id = item.getInt("id")
                    val movieTitle = item.getString("movieTitle")
                    val movieStudio= item.getString("studio")
                    val movieThumbnail = item.getString("thumbnail")
                    val movieCriticsRating = item.getString("criticsRating")

                    //data loaded to the entity
                    val moviesEntity = MoviesEntity(
                        id, movieTitle, movieStudio, movieThumbnail, movieCriticsRating
                    )

                    //using dao to insert data to the database
                    dao?.addMovie(moviesEntity)
                }
            }
        }
        //error when exception occurs
        catch (e: JSONException) {
            Log.d("fillWithImportingMovies error: ", e.toString())
        }
    }

    // loads JSON data
    private fun loadJSONArray(context: Context): JSONArray?{
        //obtain input byte
        val inputStream = context.resources.openRawResource(R.raw.movies)
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}