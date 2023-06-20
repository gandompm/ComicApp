package com.example.comicapp.presentation.navigation

import FavComicsPageScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.comicapp.presentation.comic_page.components.ComicPageScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.FavComicsPageScreen.route ){
        composable(route = Screen.ComicPageScreen.route +
                "?comicNumId={comicNumId}",
            arguments = listOf(
                navArgument(
                    name = "comicNumId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            val comicId = it.arguments?.getInt("comicNumId") ?: -1
            ComicPageScreen(navController = navController, comicNumId = comicId)
        }

        composable( route = Screen.FavComicsPageScreen.route){
            FavComicsPageScreen(navController = navController)
        }
    }

}