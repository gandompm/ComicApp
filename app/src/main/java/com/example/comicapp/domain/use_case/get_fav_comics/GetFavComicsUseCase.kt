package com.example.comicapp.domain.use_case.get_fav_comics

import com.example.comicapp.common.Resource
import com.example.comicapp.domain.model.Comic
import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavComicsUseCase  @Inject constructor(
    private val repository: ComicRepository
) {
    /**
     * Retrieves a list of favorite comics.
     *
     * @return A flow of Resource<List<Comic>> representing the result of the retrieval operation.
     */
    operator fun invoke(): Flow<Resource<List<Comic>>> {
        return flow {
            repository.getFavoriteComics().collect{
                    result ->
                emit(result)
            }
        }
    }
}