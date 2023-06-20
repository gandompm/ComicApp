package com.example.comicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This data class represents a comic entity in the application
 */
@Entity
data class Comic(
    val alt: String,
    val day: String,
    val img: String,
    val month: String,
    @PrimaryKey
    val num: Int,
    val title: String,
    val transcript: String,
    val year: String,
    var storedAsFavorite: Boolean = false
)
