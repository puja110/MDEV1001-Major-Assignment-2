package com.example.major_assignment2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.major_assignment2.ui.activity.MoviesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigating to the movie listing screen
        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }
}