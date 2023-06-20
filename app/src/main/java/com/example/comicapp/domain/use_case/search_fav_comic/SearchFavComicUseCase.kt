package com.example.comicapp.domain.use_case.search_fav_comic

import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    operator fun invoke(comicNum: Int): Flow<Boolean> {
        return flow {

            val doesExist = repository.doesComicExistsInDataBase(comicNum)
            emit(doesExist)
        }
    }
}