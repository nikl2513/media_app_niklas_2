package com.example.mediaappniklas2.presentation.startskærm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MovieData

class HomePageViewModel :  ViewModel() {
    private val _movieList = mutableStateOf<List<MovieData>>(emptyList())
    val movieList: State<List<MovieData>> get() = _movieList

    fun updateMovieList(newList: List<MovieData>) {
        _movieList.value = newList
        if (newList != null) {
            Log.d("MediaPageViewModel", "Current movie set: ${newList.size}")
            Log.d("MediaViewModel","${newList.first().movieID}")
        } else {
            Log.d("MediaPageViewModel", "Current movie set to null")
        }
    }



}