package com.example.mediaappniklas2.presentation.mediapage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MovieData

class MediaPageViewModel : ViewModel() {

    private val _currentMovie = mutableStateOf<MovieData?>(MovieData("12","Error","1900",""))
    val currentMovie: State<MovieData?> get() = _currentMovie


    fun setCurrentMovie(movie: MovieData?) {

        _currentMovie.value = movie
        if (movie != null) {
            Log.d("MediaPageViewModel", "Current movie set: ${movie.title}, ${movie.releasedate}, ${movie.imageRef}")
        } else {
            Log.d("MediaPageViewModel", "Current movie set to null")
        }
    }


}
