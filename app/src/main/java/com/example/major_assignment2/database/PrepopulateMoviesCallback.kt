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

class PrepopulateMoviesCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            prePopulateUsers(context)
        }
    }

//    suspend fun prePopulateUsers(context: Context) {
    fun prePopulateUsers(context: Context) {
        try {
            val userDao = MoviesDatabase.getDatabaseInstance(context)?.moviesDao

            val userList: JSONArray =
                context.resources.openRawResource(R.raw.movies).bufferedReader().use {
                    JSONArray(it.readText())
                }

            userList.takeIf { it.length() > 0 }?.let { list ->
                for (index in 0 until list.length()) {
                    val userObj = list.getJSONObject(index)
//                    userDao?.insertMovie(
//                        Movies(
//                            userObj.getInt("id"),
//                            userObj.getString("movie_title"),
//                            userObj.getString("studio"),
//                            userObj.getString("thumbnail"),
//                            userObj.getString("critics_rating")
//                            )
//                    )

                }
                Log.e("User App", "successfully pre-populated users into database")
            }
        } catch (exception: Exception) {
            Log.e(
                "User App",
                exception.localizedMessage ?: "failed to pre-populate users into database"
            )
        }
    }
}