package com.example.mediaappniklas2.datalayer.remote

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
        var Title : String,
        var releasedate : String,
        var imageRef : String
)




