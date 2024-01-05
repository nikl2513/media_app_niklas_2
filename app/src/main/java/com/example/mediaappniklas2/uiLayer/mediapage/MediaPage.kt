package com.example.mediaappniklas2.uiLayer.mediapage

import android.widget.RatingBar
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch


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
fun MediaPage(modifier: Modifier = Modifier.background(Color.DarkGray), movieViewModel: MediaPageViewModel, viewModel: FilmRatingViewModel = FilmRatingViewModel()) {

  //Mikkel Rating forsøg
    var rating by remember { mutableStateOf(0f) }
    var isRatingSubmitted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var gennemsnitligRating by remember { mutableStateOf(0.0) }




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
                Text(text = "avg. rating", color = Color.White)
                Icon(
                    imageVector = Icons.Default.Star, // This assumes you're using the default star icon
                    contentDescription = null, // You can provide a description if needed
                    tint = Color.White, // You can change the color of the star icon if required
                    modifier = Modifier.padding(start = 4.dp))
            }
            Text(movie.title, color = Color.White)

            LaunchedEffect(key1 = movie.title) {
                // Hent gennemsnitlig rating ved opstart
                gennemsnitligRating = viewModel.hentGennemsnitligFilmRating(movie.title)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Gennemsnitlig Rating: $gennemsnitligRating")
            if (!isRatingSubmitted) {
                RatingBar(rating = rating, onRatingChanged = { newRating ->
                    rating = newRating
                })

                Spacer(modifier = Modifier.height(16.dp))

                // Gem rating knap
                Button(onClick = {
                    // Gem rating i Firebase

                    scope.launch {
                        // Gem rating i Firebase
                        viewModel.gemFilmRating(movie.title, rating.toDouble())
                        isRatingSubmitted = true
                        gennemsnitligRating = viewModel.hentGennemsnitligFilmRating(movie.title)

                    } }) {
                    Text("Gem Rating")
                }
            } else {
                // Vis besked, når rating er gemt
                Text("Tak for din rating!")
            }
        }



        }
    }

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    var isRatingBeingChanged by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp)
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (isRatingBeingChanged || i <= rating) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier
                    .clickable {
                        onRatingChanged(i.toFloat())
                    }
                    .padding(4.dp)
                    .size(40.dp)
            )
        }
    }
}


