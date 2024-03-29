@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaappniklas2.uiLayer.startskærm


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalPlay
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocalPlay
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun importOfMovies(): List<MovieData> {
    return withContext(Dispatchers.IO) {
        try {
            val movieApiResponse: MovieApiResponse =
                RetrofitClient.movieApiService.fetchMovies(1990, 2023)

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

@Composable
private fun SectionWithVerticalList(
    sectionTitle: String,
    filmList: List<MovieData>,
    navController: NavController
) {
    Column {
        Text(text = sectionTitle, color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        VerticalList(filmList = filmList, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpstartStartskærm(
    modifier: Modifier = Modifier
        .background(colorResource(id = R.color.deep_gray))
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
    navController: NavController,
    movieViewModel: HomePageViewModel
) {
    val sections = listOf(
        Section("  Trending", movieViewModel.trendingMovies.value),
        Section("  Must watch", movieViewModel.mustWatchMovies.value),
        Section("  For you", movieViewModel.forYouMovies.value),
        Section("  All movies", movieViewModel.movieList.value)
    )
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Screen.Startskaerm.route
        ),
        NavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            route = Screen.Search.route
        ),
        NavigationItem(
            title = "List",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            route = Screen.SavedMovieList.route
        ),
        NavigationItem(
            title = "Challenges",
            selectedIcon = Icons.Filled.LocalPlay,
            unselectedIcon = Icons.Outlined.LocalPlay,
            route = Screen.Challenges.route
        )
    )
    LaunchedEffect(true) {
        if (movieViewModel.movieList.value.isEmpty()) {
            val movies = withContext(Dispatchers.IO) {
                importOfMovies()
            }
            movieViewModel.updateMovieList(movies)
            movieViewModel.getFeaturedfilm()
            movieViewModel.calculateTrendingMovies()
            movieViewModel.calculateForYouMovies()
            movieViewModel.calculateMustWatchMovies()
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                navController.navigate(item.route)
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                item.badgeCount?.let {
                                    Text(text = item.badgeCount.toString())
                                }
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Topapp(drawerState, navController)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.weekly_highlight),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    VerticalListTopHighlight(
                        filmList = movieViewModel.featuredfilm.value, navController = navController
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                }
                item {
                    MedieKnapper(navController)
                    Spacer(modifier = Modifier.height(25.dp))
                }
                items(sections) { section ->
                    SectionWithVerticalList(
                        sectionTitle = section.title,
                        filmList = section.filmList,
                        navController
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}

@Composable
fun MedieKnapper(navController: NavController) {
    MediaAppNiklas2Theme {
        Column {
            Row {
                Button(
                    onClick = { navController.navigate(Screen.Search.route) },
                    Modifier.size(120.dp, 45.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.deep_Blue))
                ) {
                    Text(
                        text = "Search",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = { navController.navigate(Screen.SavedMovieList.route) },
                    Modifier.size(120.dp, 45.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.deep_Blue))
                ) {
                    Text(
                        text = "My List", fontSize = 14.sp, textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = { navController.navigate(Screen.Challenges.route) },
                    Modifier.size(120.dp, 45.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.deep_Blue))
                ) {
                    Text(
                        text = "Challenges", fontSize = 14.sp, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topapp(drawerState: DrawerState, navController: NavController) {
    val scope = rememberCoroutineScope()
    Row {
        IconButton(onClick = {
            scope.launch {
                drawerState.open()
            }
        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(120.dp))
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "",
            Modifier.clickable { navController.navigate(Screen.Startskaerm.route) })
        Spacer(modifier = Modifier.width(120.dp))
        IconButton(onClick = {/*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)

@Composable
fun MovieItem3(film: MovieData, modifier: Modifier = Modifier, navController: NavController) {
    Image(
        painter = rememberAsyncImagePainter(film.imageRef),
        contentDescription = "",
        modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    Screen.MediaPage.route.replace(
                        "{movieID}",
                        film.movieID
                    )
                )
            },
        contentScale = ContentScale.Crop,
    )
}

data class Section(val title: String, val filmList: List<MovieData>)

@Composable
private fun VerticalList(
    filmList: List<MovieData>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyRow {
        items(filmList) { film ->
            Spacer(modifier = Modifier.width(10.dp))
            // Use the appropriate MovieItem function based on your requirements
            Box(
                modifier
                    .size(150.dp, 190.dp)
                    .background(colorResource(id = R.color.deep_gray))
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                MovieItem3(film = film, navController = navController)
            }
        }
    }
}

@Composable
fun VerticalListTopHighlight(
    modifier: Modifier = Modifier, filmList: List<MovieData>,
    navController: NavController
) {
    LazyRow(modifier = modifier) {
        items(filmList) { film ->
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier
                    .size(360.dp, 190.dp)
                    .background(colorResource(id = R.color.deep_gray))
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                MovieItem3(film = film, navController = navController)
            }
        }
    }
}
















