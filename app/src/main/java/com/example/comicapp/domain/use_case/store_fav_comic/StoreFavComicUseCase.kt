package com.example.comicapp.domain.use_case.store_fav_comic

import com.example.comicapp.domain.model.Comic
import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

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