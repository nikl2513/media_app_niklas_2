package com.example.mediaappniklas2.presentation.mediapage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
            /*Box(
                modifier
                    .size(300.dp, 380.dp)
                    .background(Color.DarkGray)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {*/
                val imageModifier = Modifier
                    .width(350.dp)
                    .height(400.dp)
                    .border(BorderStroke(1.dp, Color.Black))
                    .background(Color.Yellow)
                Image(
                    painter = rememberAsyncImagePainter(movie.imageRef),
                    contentDescription = "A movie poster",
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier
                )
            //}
            Text(movie.title)
            Text(movie.releasedate)
        }
    }
}

