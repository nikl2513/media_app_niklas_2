package com.example.mediaappniklas2.uiLayer.mediapage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    movieViewModel: MediaPageViewModel
) {
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

            val isMovieSaved = remember { mutableStateOf(false) }

            LaunchedEffect(currentMovie?.imdbID) {
                currentMovie?.imdbID?.let { movieId ->
                    movieViewModel.fetchImdbRating(currentMovie.imdbID)
                    // Check if the current movie is already saved
                    isMovieSaved.value = watchLaterManager.getWatchLaterList().any { it.movieID == currentMovie.movieID }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            val icon = remember { mutableStateOf<ImageVector>(Icons.Outlined.Add) }
            Row(modifier = Modifier) {
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
                    Icon(imageVector = if (isMovieSaved.value) Icons.Outlined.Check else Icons.Outlined.Add,
                        contentDescription = "toogle icon")
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
                Text(text = "4K ", color = Color.White)
                Text(text = "| ", color = Color.White)
                Text(text = "18 Years ", color = Color.White)
                Text(text = "| ", color = Color.White)
                Text(currentMovie.releasedate, color = Color.White)
                Text(text = " | ", color = Color.White)
                Text(text = "2 t. 32 m.", color = Color.White)
            }

        }
    }
}
@Composable
fun MovieItem(movie: MovieData, viewModel: MediaPageViewModel){
    IconButton(onClick = {viewModel.saveMovie(movie)}) {

    }
}

