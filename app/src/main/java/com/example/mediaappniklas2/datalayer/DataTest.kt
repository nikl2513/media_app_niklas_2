package com.example.mediaappniklas2.datalayer


fun getMovieData() : Movie{

    var movie : Movie

    movie = Movie("The Big Short","01-28-2016","https://m.media-amazon.com/images/I/91dC4o8mScL._AC_UF894,1000_QL80_.jpg")



    return movie;
}

data class Movie(var Title : String,var releasedate : String, var imageRef : String)
