package com.example.comicapp.presentation.fa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comicapp.common.Resource
import com.example.comicapp.domain.use_case.get_fav_comics.GetFavComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class FavComicsPageViewModel @Inject constructor(
    private val getFavComicsUseCase : GetFavComicsUseCase
) : ViewModel() {


    var state by mutableStateOf(FavComicListState())


    init {
        getFavComics()
    }


    private fun getFavComics(){

        getFavComicsUseCase().onEach {
                result ->
            when(result){
                is Resource.Success -> {
                    state = state.copy(
                        comics = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}