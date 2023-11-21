package com.example.major_assignment2.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.major_assignment2.R

class AddMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add New Movie"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primaryDarkColor)))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}