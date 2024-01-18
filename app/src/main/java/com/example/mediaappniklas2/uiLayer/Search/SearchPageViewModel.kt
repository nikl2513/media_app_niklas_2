package com.example.mediaappniklas2.uiLayer.Search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaappniklas2.datalayer.MockDataModule
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import com.example.mediaappniklas2.domainLayer.MovieUtils

class SearchPageViewModel: ViewModel() {
    companion object{
        private val _movieList = mutableStateOf<List<MovieData>>(emptyList())
        val movieList: State<List<MovieData>> get() = _movieList
        suspend fun searchMovieInAPI(searchword: String) {
            val movieApiResponse: MovieApiResponse =
                RetrofitClient.movieApiService.searchmovies(searchword)
            val resultsList: List<MovieDTO> = movieApiResponse.results
            val movieList = resultsList.map { convertToMovieData(it) }
            _movieList.value = movieList
        }
        fun search(movietitle : String){
            val mockDataModule = MockDataModule()
            val movielist = mockDataModule.createMockDataList()

            val currentList = _movieList.value.toMutableList()
            val movie  = MovieUtils.findMovieByName(movietitle,movielist)
            movie?.let {
                currentList.add(it)
            }
            _movieList.value = currentList

        }


        }
    }
