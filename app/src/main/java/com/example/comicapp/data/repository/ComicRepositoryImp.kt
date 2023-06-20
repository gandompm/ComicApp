package com.example.comicapp.data.repository

import com.example.comicapp.common.Resource
import com.example.comicapp.data.local.FavoriteComicDatabase
import com.example.comicapp.data.remote.ComicApi
import com.example.comicapp.data.remote.dto.toComic
import com.example.comicapp.domain.model.Comic
import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This is an implementation of the ComicRepository interface.
 * It serves as a bridge between the data sources (remote API and local database)
 * and the domain layer of the application
 */
class ComicRepositoryImp @Inject constructor(
    private val api: ComicApi,
    private val db: FavoriteComicDatabase
) : ComicRepository {

    val dao = db.dao

    /**
     * Retrieves a comic by its comic number from the API.
     * If comicNum is null, retrieves the current comic.
     *
     * @param comicNum The comic number of the desired comic, nullable.
     * @return A flow emitting a Resource object containing the requested comic as a success result.
     */
    override suspend fun getComic(comicNum: Int?): Flow<Resource<Comic>> {
        return flow {
            comicNum?.let { nonNullNumber ->
                // Perform actions when value is not null
                val remoteResponse = api.getComic(nonNullNumber).toComic()
                remoteResponse.storedAsFavorite = dao.doesComicExist(comicNum)
                emit(Resource.Success<Comic>(remoteResponse))

            } ?: run {
                // Perform actions when value is null
                val remoteResponse = api.getCurrentComic().toComic()
                remoteResponse.storedAsFavorite = dao.doesComicExist(remoteResponse.num)
                emit(Resource.Success<Comic>(remoteResponse))
            }
        }
    }

    /**
     * Retrieves all favorite comics from the local database.
     *
     * @return A flow emitting a Resource object containing a list of favorite comics as a success result.
     */
    override suspend fun getFavoriteComics(): Flow<Resource<List<Comic>>>{
        return flow {
            val localFavoriteComics = dao.showAllFavoriteComics()
            emit(Resource.Success<List<Comic>>(localFavoriteComics))
        }
    }

    /**
     * Stores a comic as a favorite in the local database.
     *
     * @param comic The comic to be stored as a favorite.
     * @return A flow emitting the row ID of the inserted comic.
     */
    override suspend fun storeFavoriteComic(comic: Comic): Flow<Long> {
        return flow {
            val rowId = dao.insertComic(comic)
            emit(rowId)
        }
    }

    /**
     * Checks if a comic with the given comic number exists in the local database.
     *
     * @param comicNum The comic number to check.
     * @return True if the comic exists in the database, false otherwise.
     */
    override suspend fun doesComicExistsInDataBase(comicNum: Int): Boolean {
        return dao.doesComicExist(comicNum)
    }

    /**
     * Deletes a comic with the given comic number from the local database.
     *
     * @param comicNum The comic number of the comic to delete.
     * @return The number of rows affected by the deletion operation.
     */
    override suspend fun deleteComicFromFavoriteDataBase(comicNum: Int): Int {
        return dao.deleteItemById(comicNum)
    }
}