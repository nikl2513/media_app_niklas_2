package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mediaappniklas2.GradientButton
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.presentation.mediapage.MediaPageAPP
import com.example.mediaappniklas2.presentation.startskærm.OpstartStartskærm
import com.example.mediaappniklas2.presentation.Login.LoginForm
import com.example.mediaappniklas2.presentation.Opstart.OpstartMedButtonOgBaggrund

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val movie: MovieData

    NavHost(navController = navController, startDestination = Screen.Opstart.route) {
        composable(route = Screen.Opstart.route){
            OpstartMedButtonOgBaggrund(navController = navController)
        }
        composable(route = Screen.Loginform.route){
            LoginForm(navController = navController)
        }
        composable(route = Screen.Tilmeld.route){

        }
        composable(route = Screen.Startskaerm.route){
            OpstartStartskærm(navController = navController)
        }
        composable(route =Screen.MediaPage.route, listOf(navArgument("title") { type = NavType.StringType }) ){
            val movietitle = it.arguments?.getString("title")
            MediaPageAPP(navController = navController, movietitle)
        }
        composable(route = Screen.GradientButton.route){
            GradientButton(navController = navController)
        }
    }
}
