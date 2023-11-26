package com.example.major_assignment2.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch

class EditMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var containerWindow: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val etTitle: AppCompatEditText = findViewById(R.id.etMovieTitle)
        val etStudio: AppCompatEditText = findViewById(R.id.etMovieStudio)
        val etCriticsRating: AppCompatEditText = findViewById(R.id.etMovieCriticsRating)
        val btnUpdateMovie: AppCompatButton = findViewById(R.id.btnUpdateMovie)
        containerWindow = findViewById<LinearLayoutCompat>(R.id.containerWindow)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Update Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

        val id = intent.getIntExtra("id", 1)

        //instance of database
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        btnUpdateMovie.setOnClickListener {
            // Return updated movie text to the MoviesActivity
            val data = Intent()
            data.putExtra("id", id)
            data.putExtra("movie_title", etTitle.text.toString())
            data.putExtra("movie_studio", etStudio.text.toString())
            data.putExtra("movie_rating", etCriticsRating.text.toString())
            setResult(Activity.RESULT_OK, data)
            // Close current window
            onBackPressed()
        }
    }

    /*override fun finish() {
        val returnIntent = Intent()
        returnIntent.putExtra("passed_item", itemYouJustCreated)
        // setResult(RESULT_OK);
        setResult(
            RESULT_OK,
            returnIntent
        ) //By not passing the intent in the result, the calling activity will get null data.
        super.finish()
    }*/

    override fun onBackPressed() {
        super.onBackPressed()
        // Fade animation for the background of Popup Window when you press the back button
       /* val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            containerWindow.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        containerWindow.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()*/
    }

    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
        return true
    }
}