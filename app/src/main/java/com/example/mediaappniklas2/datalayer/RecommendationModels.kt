package com.example.mediaappniklas2.datalayer
import kotlin.random.Random
class RecommendationModels {
    fun trending(movielist: List<MovieData>): List<MovieData> {

        val userrating: Float = generateRandomFloat()
        val trendy: Float = generateRandomFloat()
        val trendRating: Float = generateRandomFloat()

        // This is to make the actual ratings random since they all use the same static values do to not having Userdata to create them from.


        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }

        val sortedMovies = ratedMovies.sortedByDescending { it.second }

        val top10Movies = sortedMovies.take(10).map { it.first }

        return top10Movies
    }


    fun forYouPage(movielist: List<MovieData>): List<MovieData> {
        // These declared parameters would be averages from userdata
        val userrating: Float = 1.4f
        val trendy: Float = 2.6f
        val trendRating: Float = 4.8f

        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }

        val sortedMovies = ratedMovies.sortedByDescending { it.second }
        val top10Movies = sortedMovies.take(10).map { it.first }
        return top10Movies
    }


    fun mustWatchMovies(movielist: List<MovieData>): List<MovieData> {
        // These declared parameters would be averages from userdata
        val userrating: Float = 2f
        val trendy: Float = 1.2f
        val trendRating: Float = 7f

        val ratedMovies = movielist.map { movieData ->
            val abitaryVal: Float = generateRandomFloat()
            val rating: Float = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }

        val sortedMovies = ratedMovies.sortedByDescending { it.second }
        val top10Movies = sortedMovies.take(10).map { it.first }
        return top10Movies
    }
}
private fun calculateRating(
    userrating: Float,
    trendy: Float,
    trendRating: Float,
    abitaryVal : Float
): Float {
    val ratingValue = (userrating * trendRating + trendy * trendRating + abitaryVal * trendRating) / 3

    return ratingValue
}

fun generateRandomFloat(): Float {
    return Random.nextFloat() * 9 + 1  // Generates a random float between 1 and 10
}


