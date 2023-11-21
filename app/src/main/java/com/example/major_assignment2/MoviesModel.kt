package com.example.major_assignment2

import android.media.Rating

class MoviesModel(
    var id: Int,
    var imageUrl: String,
    var title: String,
    var studio: String,
    var rating: String,
) {
    init {
        imageUrl = imageUrl
        title = title
        studio = studio
        rating = rating
    }
}
