package com.example.major_assignment2.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.major_assignment2.R
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.database.MoviesDatabase
import kotlinx.coroutines.launch

class AddMovieActivity : AppCompatActivity() {

    private val moviesDatabase by lazy { MoviesDatabase.getDatabaseInstance(this).moviesDao() }

    /*private lateinit var moviesViewModel: MoviesViewModel*/

   /* private var moviesDao: MoviesDao? = null
    private var allMovies: LiveData<List<Movies>>? = null
    private val database = MoviesDatabase.getDatabaseInstance(this)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val btnAddMovie: AppCompatButton = findViewById(R.id.btnAddMovie)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add New Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        observeMovies()
        btnAddMovie.setOnClickListener {
//            val noteDateAdded = Date()
//            val noteText = result.data?.getStringExtra("note_text")
            // Add the new note at the top of the list
            val newMovie = Movies(1,"The Forest 2", "Gibili 2", "", "7.7")
            lifecycleScope.launch {
                moviesDatabase.addMovie(newMovie)
            }
        }
        /*moviesDao = database.moviesDao()
        allMovies = moviesDao?.getAllMovies()*/

        /*moviesViewModel = ViewModelProviders.of(this)[MoviesViewModel::class.java]
        moviesViewModel.readAllMoviesData.observe(this, Observer {
            Log.d("Movies observed", "$it")
            Log.d("Movies observed 222", "")

            //adapter.submitList(it)
        })*/
    }

    private fun observeMovies() {
        lifecycleScope.launch {
            moviesDatabase.getMovies().collect { moviesList ->
                if (moviesList.isNotEmpty()) {
                    Log.d("moviesList:::::: ", moviesList.toString())
//                    adapter.submitList(notesList)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}