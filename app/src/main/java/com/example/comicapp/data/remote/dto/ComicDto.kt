package com.example.comicapp.data.remote.dto

import com.example.comicapp.domain.model.Comic

/**
 * data transfer object the class that will be retrieved from API
 */
data class ComicDto(
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    val num: Int,
    val safe_title: String,
    val title: String,
    val transcript: String,
    val year: String
)

/**
 * converting ComicDto to Comic data class
 */
fun ComicDto.toComic(): Comic{
    return Comic(
        alt = alt,
        day = day,
        img = img,
        month = month,
        num = num,
        title = title,
        transcript = transcript,
        year = year
    )
}