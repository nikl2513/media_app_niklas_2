package com.example.mediaappniklas2.datalayer

data class trendingMovies(
    val movieData: MovieData,
    val userrating : CustomFloatValueTrending,
    val trendy : CustomFloatValueTrending,
    val trendRating : CustomFloatValueTrending
)

data class movieRating(
    val movieData: MovieData,
    val Rating : CustomFloatValueTrending
)




