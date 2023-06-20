package com.example.comicapp.domain.respository

import com.example.comicapp.common.Resource
import com.example.comicapp.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicRepository {

    suspend fun getComic(comicNum: Int?): Flow<Resource<Comic>>

    suspend fun getFavoriteComics(): Flow<Resource<List<Comic>>>

    suspend fun storeFavoriteComic(comic: Comic): Flow<Long>

    suspend fun doesComicExistsInDataBase(comicNum: Int): Boolean

    suspend fun deleteComicFromFavoriteDataBase(comicNum: Int): Int
}