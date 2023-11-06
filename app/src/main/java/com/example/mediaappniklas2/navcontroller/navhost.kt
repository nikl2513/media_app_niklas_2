package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.mediaappniklas2.Opstartapp

@Composable
fun NavHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Routes.OPSTART.path){
        composable(route = Routes.OPSTART.path){
            Opstartapp()

        }
    }
}

private enum class Routes(val path: String){
    MEDIA_PAGE("MediaPage"),
    OPSTART("Opstart")
}