import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comicapp.presentation.navigation.Screen
import com.example.comicapp.presentation.fa.FavComicsPageViewModel
import com.example.comicapp.presentation.ui.CircularProgressBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavComicsPageScreen(
    navController: NavController,
    viewModel: FavComicsPageViewModel = hiltViewModel()
) {
    val state = viewModel.state



    Column(modifier = Modifier.fillMaxSize()){

        Spacer(modifier = Modifier.height(25.dp))

            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(state.comics){
                        comic ->
                    FavComicItem(comic = comic,
                        onItemClick = {
                            navController.navigate(
                                Screen.ComicPageScreen.route +
                                "?comicNumId=${comic.num}"
                            )
                        }
                    )
                }
            }


        if(state.error.isNotBlank()){
            Text(text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally))
        }
        if (state.isLoading){
            // CircularProgressBar(percentage = 0.8f, number = 100)
        }
    }

}