package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SavedMovieList(
    viewModel: MediaPageViewModel, modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.BottomCenter
        ), navController: NavController
) {
    val watchLaterManager = WatchListManager(context = LocalContext.current)
    val WatchLaterList = watchLaterManager.getWatchLaterList()
    LazyRow {
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
        }
    }
}