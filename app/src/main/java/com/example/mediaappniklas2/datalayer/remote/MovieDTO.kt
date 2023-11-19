package com.example.mediaappniklas2.datalayer.remote

import com.google.gson.annotations.SerializedName

/**
 * @author s215698
 * This is a data class that contains the information about a movie that is needed to display it in
 * the app.
 * Title: this is the Title of the movie
 * Release date: When the movie was released
 * imageRef: This is a url for the movie poster
 * These objects are used by the viewmodel to tell it what to show.
 */
data class MovieDTO(
        @SerializedName("title")
        val Title : String,

        @SerializedName("")
        val releasedate : String,

        val imageRef : String
)

data class MovieApiResponse(
        val results : List<MovieDTO>
)



