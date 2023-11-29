package com.example.mediaappniklas2.domainLayer

import android.util.Log
import com.example.mediaappniklas2.datalayer.MovieData
object MovieUtils {
        fun findMovieById(movieId: String?, movieList: List<MovieData>): MovieData? {
            Log.d("Log","movieID : $movieId")
            Log.d("LOG", "Title : ${movieList.first().title}")
            return movieList.find { it.movieID == movieId }
        }
    }


