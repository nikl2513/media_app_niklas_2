package com.example.mediaappniklas2.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediaappniklas2.MediaPageAPP
import com.example.mediaappniklas2.OpstartStartskærm
import com.example.mediaappniklas2.Opstartapp
import com.example.mediaappniklas2.LoginForm

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.OPSTART.path) {
        composable(route = Routes.OPSTART.path) {
            Opstartapp(onNavigateToTilmeld = {
                navController.navigate(
                    route = Routes.TILMELD.path.replace("{name}", it)
                )
            },
                onNavigateToLogInd = {
                    navController.navigate(
                        route = Routes.LOGINFORM.path.replace(
                            "{name}",
                            it
                        )
                    )
                }
            )
        }
        composable(route = Routes.STARTSKAERM.path) {
            MediaPageAPP(onNavigateBack = {
                navController.navigate(
                    route = Routes.STARTSKAERM.path.replace(
                        "{name}",
                        it
                    )
                )
            })
        }
        composable(route = Routes.STARTSKAERM.path){
            OpstartStartskærm()

        }
        composable(route = Routes.LOGINFORM.path){
            LoginForm(onNavigateToStarskærm = {
                navController.navigate(
                    route = Routes.STARTSKAERM.path.replace(
                        "{name}", it
                    )
                )
            })
        }
    }
}

private enum class Routes(val path: String) {
    MEDIA_PAGE("MediaPage"),
    OPSTART("Opstart"),
    TILMELD("Tilmeld"),
    STARTSKAERM("Startskærm"),
    LOGINFORM("LoginForm")
}