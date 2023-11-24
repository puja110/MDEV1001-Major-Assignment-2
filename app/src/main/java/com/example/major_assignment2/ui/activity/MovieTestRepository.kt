package com.example.major_assignment2.ui.activity

import androidx.lifecycle.LiveData
import com.example.major_assignment2.model.Movies

interface MovieTestRepository {
    interface Local {
    }

    suspend fun getCategories(): List<Movies>
}