package com.example.mediaappniklas2.uiLayer.Search

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.uiLayer.startskærm.NavigationItem
import com.example.mediaappniklas2.uiLayer.startskærm.Topapp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier
    .background(Color.DarkGray)
    .fillMaxSize()
    .wrapContentSize(Alignment.TopCenter), navController: NavController, ) {
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
        var text by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
            Column {
                Topapp(DrawerState = drawerState)
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    /*Icon(Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { navController.navigate(Screen.Startskaerm.route) }
                    )*/
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Search") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                val scope = rememberCoroutineScope()
                Spacer(modifier = Modifier.height(6.dp))
                Button(onClick = { scope.launch { SearchPageViewModel.searchMovieInAPI(text) } }) {
                    Text(text = "Search")
                }
                Spacer(modifier = Modifier.height(6.dp))
                verticalList(
                    filmList = SearchPageViewModel.movieList.value,
                    navController = navController
                )
            }

        }
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
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}