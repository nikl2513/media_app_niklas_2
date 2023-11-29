package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mediaappniklas2.presentation.StreamingServices.GradientButton
import com.example.mediaappniklas2.domainLayer.MovieUtils
import com.example.mediaappniklas2.presentation.Login.LoginForm
import com.example.mediaappniklas2.presentation.Opstart.OpstartMedButtonOgBaggrund
import com.example.mediaappniklas2.presentation.Search.SearchBar
import com.example.mediaappniklas2.presentation.Search.SearchPageViewModel
import com.example.mediaappniklas2.presentation.mediapage.MediaPageAPP
import com.example.mediaappniklas2.presentation.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.presentation.startskærm.HomePageViewModel
import com.example.mediaappniklas2.presentation.startskærm.OpstartStartskærm

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val homePageViewModel: HomePageViewModel = viewModel()
    val mediaPageViewModel : MediaPageViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Startskaerm.route) {
        composable(route = Screen.Opstart.route){
            OpstartMedButtonOgBaggrund(navController = navController)
        }
        composable(route = Screen.Loginform.route){
            LoginForm(navController = navController)
        }
        composable(route = Screen.Tilmeld.route){

        }
        composable(route = Screen.Startskaerm.route){
            OpstartStartskærm(navController = navController,movieViewModel = homePageViewModel)
        }
        composable(route =Screen.MediaPage.route, listOf(navArgument("movieID") { type = NavType.StringType }) ){
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID,homePageViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)

            MediaPageAPP(navController = navController,movieViewModel = mediaPageViewModel)
        }
        composable(route =Screen.MediaPage2.route, listOf(navArgument("movieID") { type = NavType.StringType }) ){
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID,SearchPageViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)
            MediaPageAPP(navController = navController, movieViewModel = mediaPageViewModel)
        }
        composable(route = Screen.GradientButton.route){
            GradientButton(navController = navController)
        }
        composable(route = Screen.Search.route){
            SearchBar(navController = navController)
        }
    }
}
