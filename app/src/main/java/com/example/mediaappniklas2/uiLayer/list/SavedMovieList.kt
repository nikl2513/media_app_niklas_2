package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.withConsumedWindowInsets
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaappniklas2.datalayer.local.WatchListManager
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.MovieItem3
import com.example.mediaappniklas2.uiLayer.startskærm.MovieItem4
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.uiLayer.mediapage.TopmenuBar
import com.example.mediaappniklas2.uiLayer.startskærm.NavigationItem
import kotlinx.coroutines.launch
import androidx.compose.material3.rememberDrawerState
import com.example.mediaappniklas2.uiLayer.startskærm.HomePageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.Topapp
import com.example.mediaappniklas2.uiLayer.startskærm.verticalListTopHighlight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedMovieList(
    modifier: Modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter), navController: NavController,
    movieViewModel: HomePageViewModel
) {
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Screen.Startskaerm.route
            //tilføj route for navcontroller her
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
            title = "Your Streaming Services",
            selectedIcon = Icons.Filled.PlayArrow,
            unselectedIcon = Icons.Outlined.PlayArrow,
            route = Screen.GradientButton.route
        )

    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        val watchLaterManager = WatchListManager(context = LocalContext.current)
        val WatchLaterList = watchLaterManager.getWatchLaterList()
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
                                //her kan man tilføje navcontroller.navigate(item.route)
                                //hvor route er gemt i item
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
            Spacer(modifier = Modifier.width(16.dp))
            LazyColumn {
                item {
                    Topapp(drawerState)
                }
                items(WatchLaterList) { movie ->
                    //Spacer(modifier = Modifier.height(100.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        //modifier = modifier.background(Color.DarkGray)
                    ) {
                        MovieItem4(film = movie, navController = navController)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = movie.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }

                }
            }
        }
    }
}
/*LazyRow {
    items(WatchLaterList) { movie ->
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier
                .size(150.dp, 190.dp)
                .background(Color.DarkGray)
                .clip(shape = RoundedCornerShape(10.dp))
        ){
            MovieItem3(film = movie, navController = navController)
        }
    }*/


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