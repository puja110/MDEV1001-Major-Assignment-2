package com.example.major_assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "movie_title") val movieTitle: String?,
    @ColumnInfo(name = "studio") val studio: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "critics_rating") val criticsRating: String?
)

