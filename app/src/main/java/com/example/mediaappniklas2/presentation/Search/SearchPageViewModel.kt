package com.example.mediaappniklas2.presentation.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.presentation.startskærm.HomePageViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class SearchPageViewModel: ViewModel() {

    private val _searchtekst = MutableStateFlow("")
    val searchtekst = _searchtekst.asStateFlow()

    private val _issearchning = MutableStateFlow(false)
    val issearchning = _issearchning.asStateFlow()

    private val _ismovie = MutableStateFlow(listOf<MovieData>())
    @OptIn(FlowPreview::class)
    val ismovie = searchtekst
        .debounce(500L)
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