package com.example.mediaappniklas2.uiLayer.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.uiLayer.startskærm.NavigationItem
import com.example.mediaappniklas2.uiLayer.startskærm.Topapp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Challenges(
    modifier: Modifier = Modifier
        .background(colorResource(id = R.color.deep_gray))
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
    navController: NavController, challengesViewModel: ChallengesViewModel
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
        ),/*
        NavigationItem(
            title = "Your Streaming Services",
            selectedIcon = Icons.Filled.PlayArrow,
            unselectedIcon = Icons.Outlined.PlayArrow,
            route = Screen.GradientButton.route
        )*/
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
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    item {

                        Topapp(drawerState, navController)
                        Text(text = "YOUR CHALLENGE", fontSize = 20.sp, color = Color.White)
                        challengesViewModel.updateChallenges()
                        challengesViewModel.challengeListState.collectAsState().value.getOrNull(
                            challengesViewModel.getchallengesCompleted
                        )?.let { currentChallenge ->
                            Text(
                                text = "watch " + challengesViewModel.calculateNewGoal().toString() + " movies",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LinearDeterminateIndicator(
                                howLong = challengesViewModel.calculateProgress(currentChallenge.goal)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "You Have Watched " + challengesViewModel.moviesWatched.toString() + " Movies",
                                fontSize = 20.sp,
                                color = Color.White)
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun LinearDeterminateIndicator(howLong: Int) {
    var currentProgress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope() // Create a coroutine scope


    LaunchedEffect(key1 = true) {
        loadProgress({ progress ->
            currentProgress = progress
        }, howLong)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LinearProgressIndicator(
            progress = currentProgress,
            modifier = Modifier.fillMaxWidth().height(25.dp)

        )
    }
}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit, howLong: Int) {
    for (i in 1..howLong) {
        updateProgress(i.toFloat() / 100)
        delay(25)
    }
}


