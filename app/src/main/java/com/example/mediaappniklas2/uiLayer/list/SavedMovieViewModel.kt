package com.example.mediaappniklas2.uiLayer.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.local.WatchListManager


class SavedMovieViewModel : ViewModel(  ){
    private val _movieList = mutableStateOf<List<MovieData>>(emptyList())
    val movieList: State<List<MovieData>> get() = _movieList

    fun getList(watchListManager: WatchListManager){

        val list : List<MovieData> = watchListManager.getWatchLaterList()


        _movieList.value = list
    }



}