package com.example.comicapp.domain.respository

import com.example.comicapp.common.Resource
import com.example.comicapp.domain.model.Comic
import kotlinx.coroutines.flow.Flow

/**
 * This interface defines functions to interact with comic data either from remote or local
 */
interface ComicRepository {

    /**
     * Retrieves a comic by its comic number from the API.
     * If comicNum is null, retrieves the current comic.
     *
     * @param comicNum The comic number of the desired comic, nullable.
     * @return A flow emitting a Resource object containing the requested comic as a success result.
     */
    suspend fun getComic(comicNum: Int?): Flow<Resource<Comic>>

    /**
     * Retrieves all favorite comics from the local database.
     *
     * @return A flow emitting a Resource object containing a list of favorite comics as a success result.
     */
    suspend fun getFavoriteComics(): Flow<Resource<List<Comic>>>

    /**
     * Stores a comic as a favorite in the local database.
     *
     * @param comic The comic to be stored as a favorite.
     * @return A flow emitting the row ID of the inserted comic.
     */
    suspend fun storeFavoriteComic(comic: Comic): Flow<Long>

    /**
     * Checks if a comic with the given comic number exists in the local database.
     *
     * @param comicNum The comic number to check.
     * @return True if the comic exists in the database, false otherwise.
     */
    suspend fun doesComicExistsInDataBase(comicNum: Int): Boolean


    /**
     * Deletes a comic with the given comic number from the local database.
     *
     * @param comicNum The comic number of the comic to delete.
     * @return The number of rows affected by the deletion operation.
     */
    suspend fun deleteComicFromFavoriteDataBase(comicNum: Int): Int
}