package com.example.comicapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This annotation @HiltAndroidApp is used to generate the necessary Hilt components
 * for dependency injection in the application
 */
@HiltAndroidApp
class ComicApplication: Application() {
}