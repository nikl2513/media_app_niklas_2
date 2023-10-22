package com.example.mediaappniklas2.datalayer

import java.util.Date


fun getMovieData() : Movie{

    var movie : Movie = Movie("The Great Short","01-28-2016","https://m.media-amazon.com/images/I/91dC4o8mScL._AC_UF894,1000_QL80_.jpg")



    return movie;
}

data class Movie(var Title : String,var releasedate : String, var imageRef : String)
