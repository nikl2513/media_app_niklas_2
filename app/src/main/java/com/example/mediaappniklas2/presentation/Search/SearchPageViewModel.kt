package com.example.mediaappniklas2.presentation.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mediaappniklas2.datalayer.MovieDTO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SearchPageViewModel: ViewModel() {

    private val _searchtekst = MutableStateFlow("")
    val searchtekst = _searchtekst.asStateFlow()

    private val _issearchning = MutableStateFlow(false)
    val issearchning = _issearchning.asStateFlow()

    private val _ismovie = MutableStateFlow(listOf<MovieDTO>())
    val ismovie = searchtekst
        .combine(_ismovie) { text, movie ->
            if (text.isBlank()) {
                movie
            } else {
                movie.filter {
                    it.matchsearch(text)
                }
            }


        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _ismovie.value
        )

fun onSearchTextChange(text: String){
    _searchtekst.value = text
}






}