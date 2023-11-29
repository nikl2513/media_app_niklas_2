package com.example.mediaappniklas2.presentation.startskærm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.RecommendationModels

class HomePageViewModel :  ViewModel() {
    private val _movieList = mutableStateOf<List<MovieData>>(emptyList())
    val movieList: State<List<MovieData>> get() = _movieList


    private val _trendingMovies = mutableStateOf<List<MovieData>>(emptyList())
    val trendingMovies: State<List<MovieData>> get() = _trendingMovies
    fun updateMovieList(newList: List<MovieData>) {
        _movieList.value = newList
        if (newList != null) {
            Log.d("MediaPageViewModel", "Current movie set: ${newList.size}")
            Log.d("MediaViewModel","${newList.first().movieID}")
        } else {
            Log.d("MediaPageViewModel", "Current movie set to null")
        }
    }
    fun calculateTrendingMovies() {
        // Implement your trending calculation logic here based on _movieList
        // For example, you can use your existing RecommendationModels.trending method
        _trendingMovies.value = RecommendationModels().trending(_movieList.value)
    }




}