package com.example.comicapp.data.remote

import com.example.comicapp.common.Constants
import com.example.comicapp.data.remote.dto.ComicDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * represents an API interface for retrieving comic data from a remote server
 */
interface ComicApi {

    /**
     * Retrieves the current comic from the API.
     *
     * @return The current comic as a ComicDto object.
     */
    @GET("info.0.json")
    suspend fun getCurrentComic(): ComicDto

    /**
     * Retrieves a specific comic by its comic number from the API.
     *
     * @param comicNumber The comic number of the desired comic.
     * @return The requested comic as a ComicDto object.
     */
    @GET("{comic_num}/info.0.json")
    suspend fun getComic(@Path("comic_num") comicNumber: Int): ComicDto

}