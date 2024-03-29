package com.example.mediaappniklas2.uiLayer.mediapage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.ImdbApiResponse
import com.example.mediaappniklas2.datalayer.ImdbDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient

class MediaPageViewModel : ViewModel() {
    private val _currentMovie = mutableStateOf<MovieData?>(null)
    val currentMovie: State<MovieData?> get() = _currentMovie
    fun setCurrentMovie(movie: MovieData?) {
        _currentMovie.value = movie
        if (movie != null) {
            Log.d(
                "MediaPageViewModel",
                "Current movie set: ${movie.title}, ${movie.releasedate}, ${movie.imageRef}"
            )
        } else {
            Log.d("MediaPageViewModel", "Current movie set to null")
        }
    }

    companion object {
        private val _currentImdb = mutableStateOf<ImdbDTO?>(null)
        val currentImdb: State<ImdbDTO?> get() = _currentImdb
        suspend fun searchRating(id: String) {
            val imdbApiResponse: ImdbApiResponse =
                RetrofitClient.movieApiService.getRatings(id)
            _currentImdb.value = imdbApiResponse.results
        }
    }

    suspend fun fetchImdbRating(id: String) {
        val imdbApiResponse: ImdbApiResponse = RetrofitClient.movieApiService.getRatings(id)
        _currentImdb.value = imdbApiResponse.results
    }
}
