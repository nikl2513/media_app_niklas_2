package com.example.mediaappniklas2


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.RecommendationModels
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme
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

//private val model = RecommendationModels()

private data class Film(
    val filmName: String,
    val description: String,
    val imdb: Int,
    val genre: String,
    val image: Int)


//private val filmlist2 = model.trending(filmList)

@Composable
private fun SectionWithVerticalList(sectionTitle: String, filmList: List<MovieData>, navController: NavController) {
    Column {
        Text(text = sectionTitle, color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        verticalList(filmList = filmList, navController = navController)
    }
}


@Composable
fun OpstartStartskærm(modifier: Modifier = Modifier
    .background(Color.DarkGray)
    .fillMaxSize()
    .wrapContentSize(Alignment.TopCenter),
                      navController: NavController

)
{
    val movieList = remember { mutableStateOf<List<MovieData>>(emptyList()) }

    // Use LaunchedEffect to execute a coroutine when the Composable is first launched
    LaunchedEffect(true) {
        // Launch a coroutine to fetch movies
        val movies = withContext(Dispatchers.IO) {
            import_of_movies()
        }

        // Update the movieList with the fetched movies
        movieList.value = movies
    }
    //val recommendationModels = RecommendationModels()

    //val trending : List<MovieData> = recommendationModels.trending(movieList.value)
    //val forYouPage : List<MovieData>  = recommendationModels.forYouPage(movieList.value)
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
           Topapp()
            Spacer(modifier = Modifier.height(35.dp))
            verticalListTopHighlight(filmList = movieList.value, navController = navController)
            Spacer(modifier = Modifier.height(25.dp))

        }
        item {
            Text(text = "Streaming services", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            MedieKnapper()
            Spacer(modifier = Modifier.height(25.dp))
        }
        items(sections) { section ->
            SectionWithVerticalList(sectionTitle = section.title, filmList = movieList.value, navController)
            Spacer(modifier = Modifier.height(25.dp))
        }
    }

}


@Composable
fun MedieKnapper(
){
    MediaAppNiklas2Theme {
        Column {
            Row {
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Netflix",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Viaplay"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "HBO"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Disney+"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Apple tv"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Prime"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topapp(){
        Row() {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(120.dp))
            Image(painter = painterResource(id = R.drawable.logo1), contentDescription = "")
            Spacer(modifier = Modifier.width(120.dp))
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }


        }

}
@Composable
private fun MovieItem(film : Film, navController: NavController) {
     val imageidd: Int = film.image
    Image(modifier = Modifier.clickable {navController.navigate(Screen.MediaPage.route)},painter = painterResource(id = imageidd), contentDescription = "")

}
@Composable
private fun MovieItem2(film : MovieData) {
    AsyncImage(
        model = film.imageRef,
        placeholder = painterResource(id = R.drawable.logo1),
        error = painterResource(id = R.drawable.logo1),
        contentDescription = "The delasign logo",
    )

}
@Composable
private fun MovieItem3(film : MovieData, modifier: Modifier = Modifier, navController: NavController) {
  Image(
      painter = rememberAsyncImagePainter(film.imageRef),
      contentDescription ="",
      modifier .fillMaxSize()
          .clickable {navController.navigate(Screen.MediaPage.route)},
      contentScale = ContentScale.Crop,
      )

}
data class Section(val title: String)

val sections = listOf(
    Section("Recommended"),
    Section("New and exciting"),
    Section("Action")
)

@Composable
private fun verticalList(filmList: List<MovieData>, modifier: Modifier = Modifier, navController: NavController) {

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
}

@Composable
private fun verticalListTopHighlight(
    modifier: Modifier = Modifier,filmList : List<MovieData>,
    navController: NavController) {
    LazyRow(modifier = modifier) {
        items(filmList) { film ->

                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier
                        .size(360.dp, 190.dp)
                        .background(Color.DarkGray)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    MovieItem3(film = film, navController = navController)
                }

            }

    }
}
















