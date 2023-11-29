package com.example.mediaappniklas2.presentation.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class SearchPageViewModel: ViewModel() {

    suspend fun searchMovieInAPI(searchword : String ) : List<MovieData>{
        val movieApiResponse: MovieApiResponse = RetrofitClient.movieApiService.searchmovies(searchword)

        // Process the response as before
        val resultsList: List<MovieDTO> = movieApiResponse.results


        val movieList  = resultsList.map { convertToMovieData(it) }


    suspend fun searchMovieInAPI(searchword : String ) : List<MovieData>{
        val movieApiResponse: MovieApiResponse = RetrofitClient.movieApiService.searchmovies(searchword)

        // Process the response as before
        val resultsList: List<MovieDTO> = movieApiResponse.results


        val movieList  = resultsList.map { convertToMovieData(it) }



        return movieList
    }




}