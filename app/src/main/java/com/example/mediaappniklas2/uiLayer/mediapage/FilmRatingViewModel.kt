package com.example.mediaappniklas2.uiLayer.mediapage

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import okhttp3.internal.concurrent.Task
import java.lang.Math.ceil
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
        var GenRating = totalRating / count.toDouble()
        val upgenRating = roundToDecimal(GenRating,1)

        return if (count > 0) upgenRating else 0.0
    }
    //Help from chatgpt
    fun roundToDecimal(value: Double, decimalPlaces: Int): Double {
        val multiplier = 10.0.pow(decimalPlaces)
        return (value * multiplier).let { roundedValue ->
            if (roundedValue.isInfinite() || roundedValue.isNaN()) 0.0
            else roundedValue.toLong() / multiplier
        }
    }

    }



