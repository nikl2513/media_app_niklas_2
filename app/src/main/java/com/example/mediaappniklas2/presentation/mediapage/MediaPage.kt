package com.example.mediaappniklas2.presentation.mediapage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.navcontroller.Screen


@Composable
fun MediaPageAPP(navController: NavController, movieViewModel: MediaPageViewModel) {
    TopmenuBar(navController = navController)
    MediaPage(movieViewModel = movieViewModel)
}

@Composable
fun TopmenuBar(modifier: Modifier= Modifier
    .fillMaxSize()
    .wrapContentSize(
        Alignment.TopStart
    ),
               navController: NavController
){
    Row (modifier = modifier,


        ) {
        Button(onClick = {navController.navigate(Screen.Startskaerm.route)}) {
            Text(stringResource(R.string.arrow))
        }

    }

}
@Composable
fun MediaPage(modifier: Modifier = Modifier, movieViewModel: MediaPageViewModel) {
    val currentMovie = movieViewModel.currentMovie.value

    // Use LaunchedEffect to observe changes in currentMovie
    LaunchedEffect(currentMovie) {
        if (currentMovie != null) {
            // Do any additional setup or data loading based on the currentMovie
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        currentMovie?.let { movie ->
            Image(
                painter = rememberAsyncImagePainter(movie.imageRef),
                contentDescription = "A movie poster",
                modifier = Modifier.size(500.dp)
            )
            Text(movie.title)
            Text(movie.releasedate)
        }
    }
}

