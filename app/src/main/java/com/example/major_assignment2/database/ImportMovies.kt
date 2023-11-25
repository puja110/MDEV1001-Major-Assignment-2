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

class ImportMovies(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingNotes(context)
        }
    }

    //Filling database with the data from JSON
    private suspend fun fillWithStartingNotes(context: Context){
        //obtaining instance of data access object
        val dao = MoviesDatabase.getDatabaseInstance(context)?.moviesDao()

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val notes = loadJSONArray(context)
            if (notes != null){
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until notes.length()){
                    //variable to obtain the JSON object
                    val item = notes.getJSONObject(i)
                    //Using the JSON object to assign data
                    val noteTitle = item.getString("note-title")
                    val notesDescription = item.getString("note-description")

                    //data loaded to the entity
                    val noteEntity = Movies(
                        1,"movie", "studio", "", "9.0"
                    )

                    //using dao to insert data to the database
                    dao?.addMovie(noteEntity)
                }
            }
        }
        //error when exception occurs
        catch (e: JSONException) {
            Log.d("fillWithStartingNotes: ", e.toString())
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