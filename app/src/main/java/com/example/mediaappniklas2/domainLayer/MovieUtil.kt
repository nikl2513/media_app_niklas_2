package com.example.mediaappniklas2.domainLayer

import com.example.mediaappniklas2.datalayer.MovieData

object MovieUtils {
    fun findMovieById(movieId: String?, movieList: List<MovieData>): MovieData? {
        return movieList.find { it.movieID == movieId }
    }

    fun findMovieByName(title: String?, movieList: List<MovieData>): MovieData? {
        return movieList.find { it.title == title }
    }
}



