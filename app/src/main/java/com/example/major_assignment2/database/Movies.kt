package com.example.major_assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movies(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "movie_title") val movieTitle: String?,
    @ColumnInfo(name = "studio") val studio: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "critics_rating") val criticsRating: String?,
)

//@Entity(tableName = "movies_table")
//
//data class Movies(
//    val noteTitle:String,
//    val noteDescription:String,
//    @PrimaryKey(autoGenerate = true)
//    var id: Long = 0,
//)
