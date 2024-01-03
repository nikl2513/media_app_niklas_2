package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.navcontroller.NavHost
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.MovieItem3

/*@Composable
fun SavedMovieList(
    viewModel: MediaPageViewModel,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.BottomCenter),
    navController: NavController
) {
    val filmList = viewModel.savedMovies
    LazyRow {
        items(filmList) { film ->
            Spacer(modifier = Modifier.width(10.dp))
            // Use the appropriate MovieItem function based on your requirements
            Box(
                modifier
                    .size(150.dp, 190.dp)
                    .background(Color.DarkGray)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                MovieItem3(film = film, navController = navController)
            }
        }
    }
}*/
val LocalViewModel = staticCompositionLocalOf<MediaPageViewModel> {
    error("No ViewModel provided")
}
@Composable
fun SavedMovieListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // Få fat i viewModel, antagende at denne composable bliver kaldt inden for en scope, hvor en viewModel er tilgængelig
    val viewModel: MediaPageViewModel = viewModel()

    // Gem viewModel i en local så andre Composables kan bruge den
    CompositionLocalProvider(LocalViewModel provides viewModel) {
        SavedMovieListView(modifier, navController)
    }
}

@Composable
fun SavedMovieListView(
    modifier: Modifier,
    navController: NavController
) {
    val viewModel = LocalViewModel.current
    val filmList = viewModel.savedMovies

    LazyRow(modifier = modifier) {
        items(filmList) { film ->
            // Din kode for at vise filmen her
            MovieItem(film = film, navController = navController)
        }
    }
}

@Composable
fun MovieItem(film: MovieData, navController: NavController, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(film.imageRef),
        contentDescription ="",
        modifier.fillMaxSize()
            .clickable {navController.navigate(Screen.MediaPage.route.replace("{movieID}", film.movieID))}, contentScale = ContentScale.Crop,)
}