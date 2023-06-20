package com.example.comicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Comic(
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    @PrimaryKey
    val num: Int,
    val safe_title: String,
    val title: String,
    val transcript: String,
    val year: String,
    var storedAsFavorite: Boolean = false
)
