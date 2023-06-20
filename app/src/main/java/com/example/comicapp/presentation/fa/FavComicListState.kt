package com.example.comicapp.presentation.fa

import com.example.comicapp.domain.model.Comic


data class FavComicListState(
    val isLoading: Boolean = false,
    val comics: List<Comic> = emptyList(),
    val error: String = "",
    var selectedComicNum: Int = 0
)
