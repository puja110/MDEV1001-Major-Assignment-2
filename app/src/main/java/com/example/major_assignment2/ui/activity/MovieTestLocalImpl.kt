package com.example.major_assignment2.ui.activity

class MovieTestLocalImpl private constructor() : MovieTestRepository.Local {

    companion object {
        @Volatile
        private var instance: MovieTestRepository.Local? = null
        @Synchronized
        fun getInstance(): MovieTestRepository.Local {
            if (instance != null) {
                return instance!!
            }
            return MovieTestLocalImpl().also { instance = it }
        }
    }
}