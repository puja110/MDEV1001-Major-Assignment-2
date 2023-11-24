package com.example.major_assignment2.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.major_assignment2.R
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.database.MoviesDao
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.ui.fragment.MoviesViewModel

class AddMovieActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel

   /* private var moviesDao: MoviesDao? = null
    private var allMovies: LiveData<List<Movies>>? = null
    private val database = MoviesDatabase.getDatabaseInstance(this)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add New Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        /*moviesDao = database.moviesDao()
        allMovies = moviesDao?.getAllMovies()*/

        moviesViewModel = ViewModelProviders.of(this)[MoviesViewModel::class.java]
        moviesViewModel.readAllMoviesData.observe(this, Observer {
            Log.d("Movies observed", "$it")
            Log.d("Movies observed 222", "")

            //adapter.submitList(it)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}