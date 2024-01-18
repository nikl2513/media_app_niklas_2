package com.example.mediaappniklas2.uiLayer.startskærm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.RecommendationModels
import com.example.mediaappniklas2.domainLayer.MovieUtils

class HomePageViewModel : ViewModel() {
    private val _movieList = mutableStateOf<List<MovieData>>(emptyList())
    private val _featuredfilm = mutableStateOf<List<MovieData>>(emptyList())
    private val _trendingMovies = mutableStateOf<List<MovieData>>(emptyList())
    private val _forYouMovies = mutableStateOf<List<MovieData>>(emptyList())
    private val _mustWatchMovies = mutableStateOf<List<MovieData>>(emptyList())
    val movieList: State<List<MovieData>> get() = _movieList
    val featuredfilm: State<List<MovieData>> get() = _featuredfilm
    val trendingMovies: State<List<MovieData>> get() = _trendingMovies
    val forYouMovies: State<List<MovieData>> get() = _forYouMovies
    val mustWatchMovies: State<List<MovieData>> get() = _mustWatchMovies
    fun updateMovieList(newList: List<MovieData>) {
        _movieList.value = newList
    }

    fun getFeaturedfilm() {
        val newFilm = MovieUtils.findMovieByName("Planet Gliese", _movieList.value)
        newFilm?.let {
            _featuredfilm.value = _featuredfilm.value + listOf(it)
        }
    }

    fun calculateTrendingMovies() {
        _trendingMovies.value = RecommendationModels().trending(_movieList.value)
    }

    fun calculateForYouMovies() {
        _forYouMovies.value = RecommendationModels().forYouPage(_movieList.value)
    }

    fun calculateMustWatchMovies() {
        _mustWatchMovies.value = RecommendationModels().mustWatchMovies(_movieList.value)
    }
}