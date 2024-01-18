package com.example.mediaappniklas2.uiLayer.mediapage

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.math.pow

class FilmRatingViewModel {
    private val db = FirebaseFirestore.getInstance()
    private val ratingsCollection = db.collection("Movie ratings")
    suspend fun gemFilmRating(filmId: String, rating: Double) {
        val ratingData = hashMapOf(
            "filmId" to filmId,
            "rating" to rating
        )
        ratingsCollection.add(ratingData).await()
    }

    suspend fun hentGennemsnitligFilmRating(filmId: String): Double {
        val documents = ratingsCollection
            .whereEqualTo("filmId", filmId)
            .get()
            .await()
        var totalRating = 0.0
        var count = 0
        for (document in documents) {
            val rating = document.getDouble("rating")
            if (rating != null) {
                totalRating += rating
                count++
            }
        }
        val genRating = totalRating / count.toDouble()
        val upgenRating = roundToDecimal(genRating)
        return if (count > 0) upgenRating else 0.0
    }

    private fun roundToDecimal(value: Double): Double {
        val multiplier = 10.0.pow(1)
        return (value * multiplier).let { roundedValue ->
            if (roundedValue.isInfinite() || roundedValue.isNaN()) 0.0
            else roundedValue.toLong() / multiplier
        }
    }
}



