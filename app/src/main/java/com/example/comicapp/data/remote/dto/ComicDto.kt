package com.example.comicapp.data.remote.dto

import com.example.comicapp.domain.model.Comic

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

fun ComicDto.toComic(): Comic{
    return Comic(
        alt = alt,
        day = day,
        img = img,
        link = link,
        month = month,
        news = news,
        num = num,
        safe_title = safe_title,
        title = title,
        transcript = transcript,
        year = year
    )
}