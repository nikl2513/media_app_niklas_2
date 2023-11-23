package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediaappniklas2.GradientButton
import com.example.mediaappniklas2.MediaPageAPP
import com.example.mediaappniklas2.OpstartStartskærm
import com.example.mediaappniklas2.LoginForm
import com.example.mediaappniklas2.OpstartMedButtonOgBaggrund
import com.example.mediaappniklas2.ui.theme.SignupForm

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Opstart.route) {
        composable(route = Screen.Opstart.route){
            OpstartMedButtonOgBaggrund(navController = navController)
        }
        composable(route = Screen.Loginform.route){
            LoginForm(navController = navController)
        }
        composable(route = Screen.SignupForm.route){
        SignupForm(navController = navController)
        }
        composable(route = Screen.Startskaerm.route){
            OpstartStartskærm(navController = navController)
        }
        composable(route =Screen.MediaPage.route){
            MediaPageAPP(navController = navController)
        }
        composable(route = Screen.GradientButton.route){
            GradientButton(navController = navController)
        }
    }
}
