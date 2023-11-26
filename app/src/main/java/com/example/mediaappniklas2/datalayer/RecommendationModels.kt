package com.example.mediaappniklas2.datalayer
import kotlin.random.Random
class RecommendationModels {
    fun trending(movielist: List<MovieData>): List<MovieData> {
        // These declared parameters would be averages from userdata
        val userrating: CustomFloatValueTrending = CustomFloatValueTrending.create(1f)
        val trendy: CustomFloatValueTrending = CustomFloatValueTrending.create(4f)
        val trendRating: CustomFloatValueTrending = CustomFloatValueTrending.create(5f)

        // This is to make the actual ratings random since they all use the same static values do to not having Userdata to create them from.
        val abitaryVal: CustomFloatValueTrending = CustomFloatValueTrending.create(generateRandomFloat())

        val ratedMovies = movielist.map { movieData ->
            val rating: CustomFloatValueTrending = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }

        val sortedMovies = ratedMovies.sortedByDescending { it.second.value }

        val top10Movies = sortedMovies.take(10).map { it.first }

        return top10Movies
    }


    fun forYouPage(movielist: List<MovieData>) : List<MovieData> {
        // These declared parameters would be averages from userdata
        val userrating: CustomFloatValueTrending = CustomFloatValueTrending.create(4.5f)
        val trendy: CustomFloatValueTrending = CustomFloatValueTrending.create(7.5f)
        val trendRating: CustomFloatValueTrending = CustomFloatValueTrending.create(5.1f)

        // This is to make the actual ratings random since they all use the same static values do to not having Userdata to create them from.
        val abitaryVal: CustomFloatValueTrending = CustomFloatValueTrending.create(generateRandomFloat())

        val ratedMovies = movielist.map { movieData ->
            val rating: CustomFloatValueTrending = calculateRating(userrating, trendy, trendRating, abitaryVal)
            Pair(movieData, rating)
        }

        val sortedMovies = ratedMovies.sortedByDescending { it.second.value }

        val top10Movies = sortedMovies.take(10).map { it.first }

        return top10Movies

    }


    fun mustWatchMovies(movielist: List<MovieData>){

    }
}
private fun calculateRating(
    userrating: CustomFloatValueTrending,
    trendy: CustomFloatValueTrending,
    trendRating: CustomFloatValueTrending,
    abitaryVal : CustomFloatValueTrending
): CustomFloatValueTrending {
    val ratingValue = (userrating.value + trendy.value + abitaryVal.value ) / 3 * trendRating.value
    return CustomFloatValueTrending.create(ratingValue)
}

fun generateRandomFloat(): Float {
    return Random.nextFloat() * 9 + 1  // Generates a random float between 1 and 10
}


