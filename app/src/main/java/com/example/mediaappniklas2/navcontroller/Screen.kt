package com.example.mediaappniklas2.navcontroller

sealed class Screen(val route: String) {
    object Opstart : Screen("Opstart")
    object Startskaerm : Screen("Startskærm")
    object MediaPage : Screen("MediaPage/{movieID}")
    object Search : Screen("Search")
    object MediaPage2 : Screen("MediaPage2/{movieID}")
    object MediaPage3 : Screen("MediaPage3/{movieID}")
    object SavedMovieList : Screen("SavedMovieList")
    object Challenges : Screen("Challenges")
}