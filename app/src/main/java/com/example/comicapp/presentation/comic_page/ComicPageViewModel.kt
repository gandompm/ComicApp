package com.example.comicapp.presentation.comic_page

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comicapp.common.Resource
import com.example.comicapp.domain.use_case.delete_fav_comic.DeleteFavComicUseCase
import com.example.comicapp.domain.use_case.get_current_comic.GetCurrentComicUseCase
import com.example.comicapp.domain.use_case.search_fav_comic.SearchFavComicUseCase
import com.example.comicapp.domain.use_case.store_fav_comic.StoreFavComicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class ComicPageViewModel @Inject constructor(
    // savedStateHandle will contain navigation parameters
    private val savedStateHandle: SavedStateHandle,
    private val textToSpeech: TextToSpeech,
    private val getCurrentComicUseCase: GetCurrentComicUseCase,
    private val storeFavComicUseCase: StoreFavComicUseCase,
    private val deleteFavComicUseCase: DeleteFavComicUseCase
) : ViewModel(){

    var state by mutableStateOf(ComicPageState())

    private val longPressDuration: Long = 5000L

    init {
        savedStateHandle.get<Int>("comicNumId")?.let { comicId ->
            if (comicId > 0)
                getComic(comicId)
            else
                getComic(null)
        }
    }

    private fun getComic(comicNum: Int?){

        getCurrentComicUseCase(comicNum).onEach {
                result ->
            when(result){
                is Resource.Success -> {
                    state = state.copy(
                        comic = result.data,
                        isLoading = false,
                        error = "",
                        storedAsFavorite = result.data?.storedAsFavorite)
                    updateHighestComicIdNumber(state.comic?.num)
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

    private fun updateHighestComicIdNumber(num: Int?) {
        num?.let {
            if (num > state.highestComicNumber)
                state.highestComicNumber = num
        }
    }

    fun onComicImageLongPress() {
        state = state.copy(
            isAltTextVisible = true
        )
        viewModelScope.launch {
            delay(longPressDuration)
            state = state.copy(
                isAltTextVisible = false
            )
        }
    }

    fun onNextComicButtonClicked() {
        state.comic?.num?.let { comicNumber ->
            val nextComicNumber = comicNumber + 1
            getComic(nextComicNumber)
        }
    }

    fun onPreviousComicButtonClicked() {
        state.comic?.num?.let { comicNumber ->
            val previousComicNumber = comicNumber - 1
            getComic(previousComicNumber)
        }
    }

    fun onAddToFavoriteComicButtonClicked() {

        state.comic?.let {
            viewModelScope.launch(){
                storeFavComicUseCase(it).collect{
                        result ->

                    if (result)
                        state = state.copy(
                            storedAsFavorite = true
                        )
                }
            }
        }
    }

    fun onShowCurrentComicClicked() {
        getComic(null)
    }

    fun onShowRandomComicClicked() {

        val randomInt = Random.nextInt(1, state.highestComicNumber)
        getComic(randomInt)

    }

    fun onDeleteFromFavoriteButtonClicked() {
        state.comic?.let {
            viewModelScope.launch {
                deleteFavComicUseCase.invoke(comicNum = it.num).collect{
                    result ->
                    if (result)
                        state = state.copy(
                            storedAsFavorite = false
                        )
                }
            }
        }
    }


    fun onSpeakButtonClicked() {
        state.comic?.let {
            textToSpeech.speak(it.transcript, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }
}