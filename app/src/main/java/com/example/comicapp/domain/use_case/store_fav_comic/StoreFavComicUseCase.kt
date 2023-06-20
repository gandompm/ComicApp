package com.example.comicapp.domain.use_case.store_fav_comic

import com.example.comicapp.domain.model.Comic
import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    /**
     * Stores a favorite comic in the database.
     *
     * @param comic The comic to be stored as a favorite.
     * @return A flow of Boolean representing whether the comic was successfully stored or not.
     */
    operator fun invoke(comic: Comic): Flow<Boolean> {
        return flow {

            repository.storeFavoriteComic(comic).collect{
                    result ->

                if (result > 0)
                    emit(true)
                else{
                    emit(false)
                }
            }
        }
    }
}