package com.example.mediaappniklas2.presentation.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.presentation.startskærm.HomePageViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier
    .background(Color.DarkGray)
    .fillMaxSize()
    .wrapContentSize(Alignment.TopCenter), navController: NavController, ) {
    var text by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column {

        Row{
            Icon(Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {navController.navigate(Screen.Startskaerm.route)   }
                )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
    }
        val scope = rememberCoroutineScope()
        Button(onClick = {scope.launch { SearchPageViewModel.searchMovieInAPI(text) } }) {
            Text(text = "Search")
        }
        verticalList(filmList = SearchPageViewModel.movieList.value, navController = navController)
    }

}


@Composable
private fun MovieItem4(film : MovieData, modifier: Modifier = Modifier, navController: NavController) {
    Image(
        painter = rememberAsyncImagePainter(film.imageRef),
        contentDescription ="",
        modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    Screen.MediaPage2.route.replace("{movieID}", film.movieID)
                )
            }, contentScale = ContentScale.Crop,)

}

@Composable
private fun verticalList(filmList: List<MovieData>, modifier: Modifier = Modifier, navController: NavController) {

    LazyColumn() {
        items(filmList) { film ->
            Spacer(modifier = Modifier.width(10.dp))
            // Use the appropriate MovieItem function based on your requirements
            Box(
                modifier
                    .size(150.dp, 190.dp)
                    .background(Color.DarkGray)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                MovieItem4(film = film, navController = navController)
            }
        }
    }
}