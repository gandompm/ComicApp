package com.example.comicapp.domain.use_case.get_current_comic

import com.example.comicapp.common.Resource
import com.example.comicapp.domain.model.Comic
import com.example.comicapp.domain.respository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentComicUseCase @Inject constructor(
   private val repository: ComicRepository
) {

    /**
     * Retrieves the current comic or a specific comic with the specified comic number.
     *
     * @param comicNum The number of the comic to retrieve. If null, the current comic is fetched.
     * @return A flow of Resource<Comic> representing the result of the retrieval operation.
     */
    operator fun invoke(comicNum: Int?) : Flow<Resource<Comic>> {
        return flow {

            try {
                emit(Resource.Loading<Comic>())
                repository.getComic(comicNum).collect{
                        result ->
                    emit(result)
                }
            }
            // if we get a response code that doesn't start with 2
            catch (e: HttpException){
                emit(Resource.Error<Comic>(e.localizedMessage ?: "An unexpected error occurred"))
            }
            // if our repo can not talk to remote api, e.g. internet connection error
            catch (e: IOException){
                emit(Resource.Error<Comic>("Couldn't reach server. Check your connection"))
            }
        }

    }
}