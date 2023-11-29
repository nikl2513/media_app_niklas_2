package com.example.mediaappniklas2.presentation.Search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import com.example.mediaappniklas2.navcontroller.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun import_of_movies(): List<MovieData> {
    return withContext(Dispatchers.IO) {
        try {
            val movieApiResponse: MovieApiResponse = RetrofitClient.movieApiService.fetchMovies(1990, 2023)

            // Process the response as before
            val resultsList: List<MovieDTO> = movieApiResponse.results
            return@withContext resultsList.map { convertToMovieData(it) }

        } catch (e: IOException) {
            // Handle network error
            e.printStackTrace()
        }

        return@withContext emptyList()
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(navController: NavController) {
    val viewModel = viewModel<SearchPageViewModel>()
    val searchtext by viewModel.searchtekst.collectAsState()
    val movie by viewModel.ismovie.collectAsState()
    val issearching by viewModel.issearchning.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = searchtext,
            onValueChange = viewModel::onSearchTextChange,
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            items(movie){
                movie ->
                Text(text = "${movie.titleText}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {navController.navigate(Screen.MediaPage.route.replace("{movieID}", movie.id))}
                )

            }

        }

    }






    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth()
    )



}