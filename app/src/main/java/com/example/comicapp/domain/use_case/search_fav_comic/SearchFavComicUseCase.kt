package com.example.comicapp.domain.use_case.search_fav_comic

import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    /**
     * Searches for a favorite comic with the specified comic number.
     *
     * @param comicNum The comic number to search for.
     * @return A flow of Boolean representing whether the comic exists in the database or not.
     */
    operator fun invoke(comicNum: Int): Flow<Boolean> {
        return flow {

            val doesExist = repository.doesComicExistsInDataBase(comicNum)
            emit(doesExist)
        }
    }
}