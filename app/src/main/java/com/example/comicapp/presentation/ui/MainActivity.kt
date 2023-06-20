package com.example.comicapp.presentation.ui

import FavComicsPageScreen
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.comicapp.presentation.navigation.Screen
import com.example.comicapp.presentation.navigation.BottomNavItem
import com.example.comicapp.presentation.navigation.BottomNavigationBar
import com.example.comicapp.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var textToSpeech: TextToSpeech? = null

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Surface( color = MaterialTheme.colors.background)
           {
               val navController = rememberNavController()

               Scaffold(
                   bottomBar = {
                       BottomNavigationBar(
                           items = listOf(
                               BottomNavItem(
                                   name = "Favorite",
                                   route = Screen.FavComicsPageScreen.route,
                                   icon = Icons.Default.Favorite,
                                   badgeCount = 23
                               ),
                               BottomNavItem(
                                   name = "Home",
                                   route = Screen.ComicPageScreen.route +
                                           "?comicNumId={comicNumId}",
                                   icon = Icons.Default.Home
                               ),
                           ),
                           navController = navController,
                           onItemClick = {
                               navController.navigate(it.route)
                           }
                       )
                   }
               ) {
                   Navigation(navController = navController)
               }

           }
        }
    }
}
