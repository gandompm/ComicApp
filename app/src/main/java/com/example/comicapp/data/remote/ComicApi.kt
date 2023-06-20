package com.example.comicapp.data.remote

import com.example.comicapp.common.Constants
import com.example.comicapp.data.remote.dto.ComicDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicApi {

    @GET("info.0.json")
    suspend fun getCurrentComic(): ComicDto

    @GET("{comic_num}/info.0.json")
    suspend fun getComic(@Path("comic_num") comicNumber: Int): ComicDto

}