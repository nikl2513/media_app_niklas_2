package com.example.mediaappniklas2.uiLayer.mediapage

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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


}

