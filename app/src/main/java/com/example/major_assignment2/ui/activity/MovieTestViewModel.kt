package com.example.major_assignment2.ui.activity

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.major_assignment2.model.Movies
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieTestViewModel : ViewModel(),
    DefaultLifecycleObserver {

    private val repository by lazy { MovieTestRepositoryImpl.getInstance() }

    private val categoryUseCase = MutableLiveData<List<Movies>>()
    val categoryResponse: LiveData<List<Movies>> =
        categoryUseCase

    fun getCategoriesResponse() {
        viewModelScope.launch {
            try {
                repository.getCategories()
            } catch (error: Exception) {
                error.printStackTrace()
            }
        }
    }
    override fun onDestroy(owner: LifecycleOwner) {
        viewModelScope.cancel()
        super.onDestroy(owner)
    }
}