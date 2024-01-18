package com.example.mediaappniklas2.datalayer

import kotlin.random.Random

class RecommendationModels {
    fun trending(movielist: List<MovieData>): List<MovieData> {
        val userrating: Float = generateRandomFloat()
        val trendy: Float = generateRandomFloat()
        val trendRating: Float = generateRandomFloat()
        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }
        val sortedMovies = ratedMovies.sortedByDescending { it.second }
        return sortedMovies.take(10).map { it.first }
    }

    fun forYouPage(movielist: List<MovieData>): List<MovieData> {
        val userrating = 1.4f
        val trendy = 2.6f
        val trendRating = 4.8f
        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }
        val sortedMovies = ratedMovies.sortedByDescending { it.second }
        return sortedMovies.take(10).map { it.first }
    }

    fun mustWatchMovies(movielist: List<MovieData>): List<MovieData> {
        val userrating = 2f
        val trendy = 1.2f
        val trendRating = 7f
        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }
        val sortedMovies = ratedMovies.sortedByDescending { it.second }
        return sortedMovies.take(10).map { it.first }
    }
}

private fun calculateRating(
    userrating: Float,
    trendy: Float,
    trendRating: Float,
    abitaryVal: Float
): Float {
    return (userrating * trendRating + trendy * trendRating + abitaryVal * trendRating) / 3
}

fun generateRandomFloat(): Float {
    return Random.nextFloat() * 9 + 1
}


