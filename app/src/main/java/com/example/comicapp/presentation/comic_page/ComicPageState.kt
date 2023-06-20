package com.example.comicapp.presentation.comic_page

import com.example.comicapp.domain.model.Comic

data class ComicPageState(
    val isLoading: Boolean = false,
    val comic: Comic ?= null,
    val error: String = "",
    val isAltTextVisible: Boolean = false,
    val storedAsFavorite: Boolean? = false,
    var highestComicNumber: Int = 1
)
