package com.example.major_assignment2.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch

class AddMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val btnAddMovie: AppCompatButton = findViewById(R.id.btnAddMovie)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        //instance of database
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        btnAddMovie.setOnClickListener {
            val random = kotlin.math.abs((0..999999999999).random())
            Log.d("random number: ", random.toString())
            val newMovie = MoviesEntity(
                101, //Add randomly generated 3 digit Integer here
                "The Forest",
                "Gibili",
                "",
                "7.5"
            )
            lifecycleScope.launch {
                viewModel.addMovie(newMovie)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}