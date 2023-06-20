package com.example.comicapp.presentation.navigation

sealed class Screen(
    val route: String
)
{
    object ComicPageScreen : Screen("comic_page_screen")
    object FavComicsPageScreen: Screen("fav_comic_list_screen")
}