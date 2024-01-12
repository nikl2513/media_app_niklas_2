package com.example.mediaappniklas2.uiLayer.mediapage

import android.widget.RatingBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.local.WatchListManager
import com.example.mediaappniklas2.navcontroller.Screen
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.LocalPlay
import androidx.compose.material.icons.outlined.LocalPlay
import com.example.mediaappniklas2.datalayer.local.WatchedHistoryManager
import com.example.mediaappniklas2.uiLayer.challenges.ChallengesViewModel

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
            onClick = { navController.popBackStack() },
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
fun MediaPage(
    modifier: Modifier = Modifier.background(Color.DarkGray),
    movieViewModel: MediaPageViewModel,
    filmviewModel: FilmRatingViewModel = FilmRatingViewModel()
) {

    //Mikkel Rating forsøg
    var rating by remember { mutableStateOf(0f) }
    var isRatingSubmitted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var gennemsnitligRating by remember { mutableStateOf(0.0) }
    val viewModel: MediaPageViewModel = viewModel()
    val icon = remember {
        mutableStateOf<ImageVector>(Icons.Outlined.Add)
    }
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
            val watchLaterManager = WatchListManager(LocalContext.current)
            val watchedHistoryManager = WatchedHistoryManager(LocalContext.current)
            val isMovieSaved = remember { mutableStateOf(false) }
            val hasMovieBeenSeen = remember {
                mutableStateOf(false)
            }


            LaunchedEffect(currentMovie?.imdbID) {
                currentMovie?.imdbID?.let { movieId ->
                    movieViewModel.fetchImdbRating(currentMovie.imdbID)
                    // Check if the current movie is already saved
                    isMovieSaved.value = watchLaterManager.getWatchLaterList()
                        .any { it.movieID == currentMovie.movieID }
                }
            }

            LaunchedEffect(currentMovie?.imdbID) {
                currentMovie?.imdbID?.let { movieId ->
                    movieViewModel.fetchImdbRating(currentMovie.imdbID)
                    // Check if the current movie is already saved
                    hasMovieBeenSeen.value = watchedHistoryManager.getWatchedHistoryList()
                        .any { it.movieID == currentMovie.movieID }
                }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                val icon = remember { mutableStateOf<ImageVector>(Icons.Outlined.Add) }
                Row(modifier = Modifier) {
                    OutlinedButton(onClick = {
                        if (!hasMovieBeenSeen.value){
                            watchedHistoryManager.addToWatchedHistory(currentMovie)
                        }
                        else{

                        }
                        hasMovieBeenSeen.value = !hasMovieBeenSeen.value
                    },
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Black)
                        ) {
                        Icon(imageVector = if (hasMovieBeenSeen.value) Icons.Filled.LocalPlay else Icons.Outlined.LocalPlay, contentDescription = "watched movies")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedButton(
                        onClick = {
                            if (!isMovieSaved.value) {
                                // If the movie is not saved, save it
                                watchLaterManager.addToWatchLater(
                                    MovieData(
                                        movieID = currentMovie?.movieID ?: "",
                                        imdbID = currentMovie?.imdbID ?: "",
                                        title = currentMovie?.title ?: "",
                                        releasedate = currentMovie?.releasedate ?: "",
                                        imageRef = currentMovie?.imageRef ?: ""
                                    )
                                )
                            } else {

                                watchLaterManager.removeFromWatchLater(
                                    MovieData(
                                        movieID = currentMovie?.movieID ?: "",
                                        imdbID = currentMovie?.imdbID ?: "",
                                        title = currentMovie?.title ?: "",
                                        releasedate = currentMovie?.releasedate ?: "",
                                        imageRef = currentMovie?.imageRef ?: ""
                                    )
                                )
                            }
                            // Toggle the button icon
                            isMovieSaved.value = !isMovieSaved.value
                        },
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Black)
                    )
                    {
                        Icon(
                            imageVector = if (isMovieSaved.value) Icons.Outlined.Check else Icons.Outlined.Add,
                            contentDescription = "toogle icon"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    movie.title,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = R.drawable.imdb),
                        contentDescription = "logo",
                        Modifier.width(35.dp)
                    )
                    Text(text = " ", color = Color.White)
                    Text(text = currentImdb.value?.averageRating.toString(), color = Color.White)
                    Text(text = "| ", color = Color.White)
                    Text(currentMovie.releasedate, color = Color.White)
                    Text(text = " | ", color = Color.White)

                }

                LaunchedEffect(key1 = movie.title) {
                    // Fetch average rating at startup
                    gennemsnitligRating = filmviewModel.hentGennemsnitligFilmRating(movie.title)
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text("Gennemsnitlig Rating: $gennemsnitligRating")

                if (!isRatingSubmitted) {
                    RatingBar(rating = rating, onRatingChanged = { newRating ->
                        rating = newRating
                    })

                    Spacer(modifier = Modifier.height(16.dp))

                    // Save rating button
                    Button(onClick = {
                        scope.launch {
                            filmviewModel.gemFilmRating(movie.title, rating.toDouble())
                            isRatingSubmitted = true
                            gennemsnitligRating =
                                filmviewModel.hentGennemsnitligFilmRating(movie.title)
                        }
                    }) {
                        Text("Save Rating")
                    }
                } else {
                    // Thank you message for rated movie
                    Text("Thanks for your rating!")
                }
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

@Composable
fun MovieItem(movie: MovieData, viewModel: MediaPageViewModel) {
    IconButton(onClick = { viewModel.saveMovie(movie) }) {

    }
}

