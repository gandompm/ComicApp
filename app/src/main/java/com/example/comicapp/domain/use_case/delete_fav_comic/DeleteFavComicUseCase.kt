package com.example.comicapp.domain.use_case.delete_fav_comic

import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * a use case for deleting a favorite comic
 *
 * injecting repository dependency in the constructor
 */
class DeleteFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    /**
    * Deletes a favorite comic with the specified comic number.
    *
    * @param comicNum The number of the comic to delete.
    * @return A flow of Boolean representing the result of the delete operation.
    */
    operator fun invoke(comicNum: Int): Flow<Boolean> {
        return flow {

            val responseId = repository.deleteComicFromFavoriteDataBase(comicNum)

            if (responseId > 0)
                emit(true)
            else{
                emit(false)
            }
        }
    }
}