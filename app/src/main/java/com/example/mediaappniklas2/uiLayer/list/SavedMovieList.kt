package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.MovieItem3

@Composable
fun SavedMovieList(
    viewModel: MediaPageViewModel, modifier: Modifier = Modifier, navController: NavController
) {
    LazyRow {
        items(viewModel.savedMovies) { movie ->
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier
                    .size(150.dp, 190.dp)
                    .background(Color.DarkGray)
                    .clip(shape = RoundedCornerShape(10.dp))
            ){
                MovieItem3(film = movie, navController = navController)
            }
        }
    }
}/**/


/*val LocalViewModel = staticCompositionLocalOf<MediaPageViewModel> {
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
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel = LocalViewModel.current
    val filmList = viewModel.savedMovies

    LazyRow(modifier = modifier) {
        items(filmList) { film ->
            // Din kode for at vise filmen her
            MovieItem(movie = film, viewModel = viewModel)
        }
    }
}

/*@Composable
fun MovieItem(film: MovieData, navController: NavController, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(film.imageRef),
        contentDescription ="",
        modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    Screen.MediaPage.route.replace(
                        "{movieID}",
                        film.movieID
                    )
                )
            }, contentScale = ContentScale.Crop,)
}*/