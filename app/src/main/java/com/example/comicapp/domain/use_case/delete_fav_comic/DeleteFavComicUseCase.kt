package com.example.comicapp.domain.use_case.delete_fav_comic

import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {

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