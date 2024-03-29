package com.example.mediaappniklas2.navcontroller

import WatchedHistoryManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mediaappniklas2.domainLayer.MovieUtils
import com.example.mediaappniklas2.uiLayer.Opstart.OpstartMedButtonOgBaggrund
import com.example.mediaappniklas2.uiLayer.Search.SearchBar
import com.example.mediaappniklas2.uiLayer.Search.SearchPageViewModel
import com.example.mediaappniklas2.uiLayer.challenges.Challenges
import com.example.mediaappniklas2.uiLayer.challenges.ChallengesViewModel
import com.example.mediaappniklas2.uiLayer.list.SavedMovieList
import com.example.mediaappniklas2.uiLayer.list.SavedMovieViewModel
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageAPP
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.HomePageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.OpstartStartskærm

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val homePageViewModel: HomePageViewModel = viewModel()
    val mediaPageViewModel: MediaPageViewModel = viewModel()
    val watchedHistoryManager = WatchedHistoryManager.getInstance(LocalContext.current)
    val challengesViewModel = ChallengesViewModel(watchedHistoryManager).apply { createList() }
    val savedMovieViewModel: SavedMovieViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.Opstart.route) {
        composable(route = Screen.Opstart.route) {
            OpstartMedButtonOgBaggrund(navController = navController)
        }
        composable(route = Screen.Startskaerm.route) {
            OpstartStartskærm(navController = navController, movieViewModel = homePageViewModel)
        }
        composable(
            route = Screen.MediaPage.route,
            listOf(navArgument("movieID") { type = NavType.StringType })
        ) {
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID, homePageViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)

            MediaPageAPP(navController = navController, movieViewModel = mediaPageViewModel)
        }
        composable(
            route = Screen.MediaPage2.route,
            listOf(navArgument("movieID") { type = NavType.StringType })
        ) {
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID, SearchPageViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)
            MediaPageAPP(navController = navController, movieViewModel = mediaPageViewModel)
        }
        composable(
            route = Screen.MediaPage3.route,
            listOf(navArgument("movieID") { type = NavType.StringType })
        ) {
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID, savedMovieViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)

            MediaPageAPP(navController = navController, movieViewModel = mediaPageViewModel)
        }
        composable(route = Screen.Search.route) {
            SearchBar(navController = navController)
        }
        composable(route = Screen.SavedMovieList.route) {
            SavedMovieList(navController = navController, savedMovieViewModel = savedMovieViewModel)
        }
        composable(route = Screen.Challenges.route) {
            challengesViewModel.checkUncompletedChallenges()
            Challenges(navController = navController, challengesViewModel = challengesViewModel)
        }
    }
}