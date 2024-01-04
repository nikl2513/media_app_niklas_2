package com.example.mediaappniklas2.uiLayer.mediapage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.navcontroller.Screen


@Composable
fun MediaPageAPP(
    navController: NavController,
    movieViewModel: MediaPageViewModel,
) {
    MediaPage(movieViewModel = movieViewModel)
    TopmenuBar(navController = navController)
}

@Composable
fun TopmenuBar(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.TopStart
        ),
    navController: NavController
) {
    Box(
        modifier = modifier
            .alpha(alpha = 0.5f),
        contentAlignment = Alignment.TopEnd

    ) {
        IconButton(
            onClick = { navController.navigate(Screen.Startskaerm.route) },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 20.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Backbutton"
            )

        }

    }

}

@Composable
fun MediaPage(modifier: Modifier = Modifier.background(Color.DarkGray), movieViewModel: MediaPageViewModel) {
    val currentMovie = movieViewModel.currentMovie.value
    LaunchedEffect(currentMovie?.imdbID) {
        currentMovie?.imdbID?.let { movieId ->
            movieViewModel.fetchImdbRating(currentMovie.imdbID)
        }
    }
    val currentImdb = MediaPageViewModel.currentImdb

    // Use LaunchedEffect to observe changes in currentMovie

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        currentMovie?.let { movie ->
            val imageModifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
            Image(
                painter = rememberAsyncImagePainter(movie.imageRef),
                contentDescription = "A movie poster",
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.imdb),
                    contentDescription = "logo",
                    Modifier.width(35.dp)
                )
                Text(text = " ", color = Color.White)
                Text(text = currentImdb.value?.averageRating.toString(), color = Color.White)
                Text(text = "| ", color = Color.White)
                Text(text = "4K ", color = Color.White)
                Text(text = "| ", color = Color.White)
                Text(text = "18 Years ", color = Color.White)
                Text(text = "| ", color = Color.White)
                Text(currentMovie.releasedate, color = Color.White)
                Text(text = " | ", color = Color.White)
                Text(text = "2 t. 32 m.", color = Color.White)
                Text(text = " | ", color = Color.White)

            }
            Row(modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                when (index) {
                                    0 -> { /* Action for the first icon */ }
                                    1 -> { /* Action for the second icon */ }
                                    2 -> { /* Action for the third icon */ }
                                    3 -> { /* Action for the fourth icon */ }
                                    4 -> { /* Action for the fifth icon */ }
                                }
                            }
                    )
                }
            }
                Text(movie.title, color = Color.White)
            }
        }
    }


