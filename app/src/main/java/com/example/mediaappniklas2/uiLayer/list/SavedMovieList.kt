package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaappniklas2.datalayer.local.WatchListManager
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.MovieItem4

@Composable
fun SavedMovieList(
    viewModel: MediaPageViewModel, modifier: Modifier = Modifier
        .fillMaxSize(), navController: NavController
) {
    val watchLaterManager = WatchListManager(context = LocalContext.current)
    val WatchLaterList = watchLaterManager.getWatchLaterList()
    LazyColumn{
        items(WatchLaterList){movie ->
            Spacer(modifier = Modifier.width(100.dp))
            Box(
                modifier
                    .size(100.dp, 190.dp)
                    .background(Color.DarkGray)
                    .clip(shape = RoundedCornerShape(10.dp))){
                MovieItem4(film = movie, navController = navController)
                Spacer(modifier = Modifier.width(100.dp))}
            }
        }
    }
