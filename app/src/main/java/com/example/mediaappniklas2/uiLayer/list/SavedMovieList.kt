package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalPlay
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocalPlay
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.local.WatchListManager
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.uiLayer.startskærm.NavigationItem
import com.example.mediaappniklas2.uiLayer.startskærm.Topapp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedMovieList(
    modifier: Modifier = Modifier
        .background(colorResource(id = R.color.deep_gray))
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter), navController: NavController,
    savedMovieViewModel: SavedMovieViewModel
) {
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.deep_gray)
    ) {
        val watchListManager = WatchListManager(LocalContext.current)
        savedMovieViewModel.getList(watchListManager)
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
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
            Row {
                Spacer(modifier = Modifier.width(5.dp))
                LazyColumn {
                    item {
                        Topapp(drawerState, navController)
                    }
                    items(savedMovieViewModel.movieList.value) { movie ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            MovieItem4(
                                film = movie,
                                navController = navController
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = movie.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem4(film: MovieData, modifier: Modifier = Modifier, navController: NavController) {
    Image(
        painter = rememberAsyncImagePainter(film.imageRef),
        contentDescription = "",
        modifier
            .size(175.dp, 100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(
                    Screen.MediaPage3.route.replace(
                        "{movieID}",
                        film.movieID
                    )
                )
            },
        contentScale = ContentScale.Crop,
    )
}