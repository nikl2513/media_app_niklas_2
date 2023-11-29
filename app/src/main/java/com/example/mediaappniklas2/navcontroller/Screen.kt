package com.example.mediaappniklas2.navcontroller

sealed class Screen(val route: String){
    object Opstart : Screen("Opstart")
    object Loginform : Screen("LoginForm")
    object Tilmeld : Screen("Tilmeld")
    object Startskaerm : Screen("Startskærm")
    object MediaPage : Screen("MediaPage/{movieID}")
    object GradientButton : Screen("GradientButton")
    object Search : Screen("GradientButton")
}
