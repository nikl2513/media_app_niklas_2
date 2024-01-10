package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mediaappniklas2.domainLayer.MovieUtils
import com.example.mediaappniklas2.uiLayer.Login.LoginForm
import com.example.mediaappniklas2.uiLayer.Opstart.OpstartMedButtonOgBaggrund
import com.example.mediaappniklas2.uiLayer.Search.SearchBar
import com.example.mediaappniklas2.uiLayer.Search.SearchPageViewModel
import com.example.mediaappniklas2.uiLayer.StreamingServices.GradientButton
import com.example.mediaappniklas2.uiLayer.list.SavedMovieList
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageAPP
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.HomePageViewModel
import com.example.mediaappniklas2.uiLayer.startskærm.OpstartStartskærm

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val homePageViewModel: HomePageViewModel = viewModel()
    val mediaPageViewModel : MediaPageViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Startskaerm.route/*Opstart.route*/) {
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
        composable(route = Screen.MediaPage.route, listOf(navArgument("movieID") { type = NavType.StringType }) ){
            val movieID = it.arguments?.getString("movieID")
            val movie = MovieUtils.findMovieById(movieID,homePageViewModel.movieList.value)
            mediaPageViewModel.setCurrentMovie(movie)

            MediaPageAPP(navController = navController,movieViewModel = mediaPageViewModel)
        }
        composable(route = Screen.MediaPage2.route, listOf(navArgument("movieID") { type = NavType.StringType }) ){
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

        composable(route = Screen.SavedMovieList.route){
            SavedMovieList(navController = navController, movieViewModel =  homePageViewModel)
        }
    }
}
