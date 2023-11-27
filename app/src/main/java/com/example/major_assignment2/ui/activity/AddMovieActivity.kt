package com.example.major_assignment2.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import com.github.drjacky.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddMovieActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var mCameraUri: Uri? = null
    private var ivMovie: ImageView? = null

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                mCameraUri = it.data?.data
                Glide.with(this)
                    .load(mCameraUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivMovie!!)
            } else {
                parseError(it)
            }
        }

    private fun parseError(activityResult: ActivityResult) {
        if (activityResult.resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(activityResult.data), Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(this, "Task Cancelled!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val btnAddMovie = findViewById<Button>(R.id.btnAddMovie)
        val tilMovieTitle = findViewById<TextInputLayout>(R.id.tilMovieTitle)
        val etMovieTitle = findViewById<TextInputEditText>(R.id.etMovieTitle)
        val tilMovieStudio = findViewById<TextInputLayout>(R.id.tilMovieStudio)
        val etMovieStudio = findViewById<TextInputEditText>(R.id.etMovieStudio)
        val tilMovieRating = findViewById<TextInputLayout>(R.id.tilMovieRating)
        val etMovieRating = findViewById<TextInputEditText>(R.id.etMovieRating)
        val btnCancelAddMovie = findViewById<AppCompatButton>(R.id.btnCancelAddMovie)
        ivMovie = findViewById(R.id.ivMovie)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add New Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        //instance of database
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        ivMovie?.setOnClickListener {
            cameraLauncher.launch(
                ImagePicker.with(this)
                    .crop()
                    .cameraOnly()
                    .maxResultSize(1080, 1920, true)
                    .createIntent()
            )
        }

        btnAddMovie.setOnClickListener {
            val title = etMovieTitle.text.toString()
            val studio = etMovieStudio.text.toString()
            val rating = etMovieRating.text.toString()

            if (title.isEmpty()) {
                tilMovieTitle.error = "Movie title field cannot be empty!"
            } else if (studio.isEmpty()) {
                tilMovieStudio.error = "Studio field cannot be empty!"
            } else if (rating.isEmpty()) {
                tilMovieRating.error = "Rating field cannot be emtpty!"
            } else {
                val entity = MoviesEntity(
                    movieTitle = title,
                    studio = studio,
                    thumbnail = mCameraUri.toString(),
                    criticsRating = rating
                )
                viewModel.addMovie(entity)
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this, "Movie Added successfully!", Toast.LENGTH_LONG).show()
                    onBackPressedDispatcher.onBackPressed()
                }, 1200)
            }
        }

        btnCancelAddMovie.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}