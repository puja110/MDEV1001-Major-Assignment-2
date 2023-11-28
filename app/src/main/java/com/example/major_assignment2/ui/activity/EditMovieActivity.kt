package com.example.major_assignment2.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        // defining and initializing views
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val tilMovieTitle = findViewById<TextInputLayout>(R.id.tilMovieTitle)
        val etTitle: AppCompatEditText = findViewById(R.id.etMovieTitle)
        val tilMovieStudio = findViewById<TextInputLayout>(R.id.tilMovieStudio)
        val etStudio: AppCompatEditText = findViewById(R.id.etMovieStudio)
        val tilMovieRating = findViewById<TextInputLayout>(R.id.tilMovieRating)
        val etCriticsRating: AppCompatEditText = findViewById(R.id.etMovieCriticsRating)
        val ivMovieImage: ImageView = findViewById(R.id.ivMovieImage)
        val btnUpdateMovie: AppCompatButton = findViewById(R.id.btnUpdateMovie)
        val btnCancelEditMovie: AppCompatButton = findViewById(R.id.btnCancelEditMovie)

        // setting toolbar in the edit movie screen
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Update Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        val id = intent.getIntExtra("id", 1)
        val title = intent.getStringExtra("title")
        val studio = intent.getStringExtra("studio")
        val rating = intent.getStringExtra("rating")
        val thumbnail = intent.getStringExtra("thumbnail")

        //setting texts to views
        etTitle.setText(title)
        etStudio.setText(studio)
        etCriticsRating.setText(rating)

        //set image to imageview
        Glide.with(applicationContext)
            .load(thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivMovieImage)

        //instance of database
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        // adding validations and sending new movie data to execute update movie operation
        btnUpdateMovie.setOnClickListener {
            val title = etTitle.text.toString()
            val studio = etStudio.text.toString()
            val rating = etCriticsRating.text.toString()

            if (title.isEmpty()) {
                tilMovieTitle.error = "Movie title field cannot be empty!"
            } else if (studio.isEmpty()) {
                tilMovieStudio.error = "Studio field cannot be empty!"
            } else if (rating.isEmpty()) {
                tilMovieRating.error = "Rating field cannot be emtpty!"
            } else {
                // Return updated movie data to the MoviesActivity
                val data = Intent()
                data.putExtra("id", id)
                data.putExtra("movie_title", etTitle.text.toString())
                data.putExtra("movie_studio", etStudio.text.toString())
                data.putExtra("movie_rating", etCriticsRating.text.toString())
                data.putExtra("movie_thumbnail", thumbnail)
                setResult(Activity.RESULT_OK, data)

                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this, "Movie Updated successfully!", Toast.LENGTH_LONG).show()
                    onBackPressedDispatcher.onBackPressed()
                }, 1200)
            }
        }

        // user will navigate to the movie list screen upon cancel button click
        btnCancelEditMovie.setOnClickListener {
            etTitle.text?.clear()
            etStudio.text?.clear()
            etCriticsRating.text?.clear()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MoviesActivity::class.java)
                startActivity(intent)
            }, 500)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}