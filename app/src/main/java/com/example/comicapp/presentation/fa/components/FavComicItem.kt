import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.comicapp.domain.model.Comic
import com.example.comicapp.presentation.ui.CircularProgressBar

@Composable
fun FavComicItem(
    comic: Comic,
    onItemClick: (Comic) -> Unit
){

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(comic) }
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 120.dp, width = 0.dp),
                model = comic.img,
                loading = {
                    CircularProgressBar(percentage = 0.8f, number = 100)
                },
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = comic.title,
                    color = MaterialTheme.colors.primaryVariant,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Default
                )
            }

        }

    }
}