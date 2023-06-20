package com.example.comicapp.presentation.comic_page.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.comicapp.R
import com.example.comicapp.presentation.comic_page.ComicPageViewModel
import com.example.comicapp.presentation.ui.CircularProgressBar



@Composable
fun ComicPageScreen (
    navController: NavController,
    comicNumId: Int,
    viewModel: ComicPageViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    state.comic?.let {

        Box(
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column() {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement =
                    Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(0.15f)){
                        Text(
                            text = it.num.toString(),
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(modifier = Modifier.weight(0.7f).fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = it.title,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Cursive
                        )
                    }
                    Box(modifier = Modifier.weight(0.15f)){
                        if(state.comic.transcript.isNotEmpty()){
                            Icon(painter = painterResource(id = R.drawable.baseline_volume_up_24),
                                contentDescription = "speak",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { viewModel.onSpeakButtonClicked() })
                        }
                        else{
                            Icon(painter = painterResource(id = R.drawable.baseline_volume_off_24),
                                contentDescription = "can not speak",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context, "There is no Transcription to be played!",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    })
                        }

                    }
                }




                Box(
                    modifier = Modifier
                            .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                viewModel.onComicImageLongPress()
                            }
                        )
                    }
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(height = 400.dp, width = 0.dp),
                        model = it.img,
                        loading = {
                            CircularProgressBar(percentage = 0.8f, number = 100)
                        },
                        contentDescription = null
                    )
                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement =
                    Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(painter = painterResource(id = R.drawable.baseline_shuffle_24),
                        contentDescription = "random",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { viewModel.onShowRandomComicClicked() })

                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = "previous",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { viewModel.onPreviousComicButtonClicked() })

                    OutlinedButton(
                        onClick = {
                            viewModel.onShowCurrentComicClicked()
                        },
                        modifier = Modifier.
                        padding(16.dp)
                    ) {
                        Text(
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Default,
                            color= Color.DarkGray,
                            text = stringResource(id = R.string.show_current_comic))
                    }

                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = "next",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { viewModel.onNextComicButtonClicked() })

                    if (state.storedAsFavorite == true){
                        Icon(painter = painterResource(id = R.drawable.baseline_bookmark_added_24),
                            contentDescription = "favorite",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    viewModel.onDeleteFromFavoriteButtonClicked()
                                })

                    }
                    else{
                        Icon(painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                            contentDescription = "favorite",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { viewModel.onAddToFavoriteComicButtonClicked() })

                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement =
                    Arrangement.SpaceBetween
                ) {

                    if(state.isAltTextVisible){
                        Text(text = state.comic.alt)
                    }
                }

                Text(
                    text = "${it.day}.${it.month}.${it.year}",
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                )

            }
        }
    }


    if(state.error.isNotBlank()){
        Toast
            .makeText(
                context, state.error,
                Toast.LENGTH_LONG
            )
            .show()
    }
    if (state.isLoading){
//        Toast.makeText(
//                context, "Loading..",
//                Toast.LENGTH_SHORT
//            )
//            .show()
    }
}



