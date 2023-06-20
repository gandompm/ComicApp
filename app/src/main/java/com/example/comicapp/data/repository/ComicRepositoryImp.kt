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

class ComicRepositoryImp @Inject constructor(
    private val api: ComicApi,
    private val db: FavoriteComicDatabase
) : ComicRepository {

    val dao = db.dao

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

    override suspend fun getFavoriteComics(): Flow<Resource<List<Comic>>>{
        return flow {
            val localFavoriteComics = dao.showAllFavoriteComics()
            emit(Resource.Success<List<Comic>>(localFavoriteComics))
        }
    }

    override suspend fun storeFavoriteComic(comic: Comic): Flow<Long> {
        return flow {
            val isStored = dao.insertComic(comic)
            emit(isStored)
        }
    }

    override suspend fun doesComicExistsInDataBase(comicNum: Int): Boolean {
        return dao.doesComicExist(comicNum)
    }

    override suspend fun deleteComicFromFavoriteDataBase(comicNum: Int): Int {
        return dao.deleteItemById(comicNum)
    }
}