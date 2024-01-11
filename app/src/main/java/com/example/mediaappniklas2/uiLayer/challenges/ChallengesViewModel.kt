package com.example.mediaappniklas2.uiLayer.challenges

import androidx.lifecycle.ViewModel

class ChallengesViewModel: ViewModel(){
    private val  _moviesWatched : Int = 0;
    private val _challengesCompleted : Int = 0;

    val moviesWatched : Int = _moviesWatched
    val challengesCompleted : Int = _challengesCompleted

    fun watchedMovie(){
        _moviesWatched.inc()

    }

    fun challengeCompleted(){
        _challengesCompleted.inc()
    }


}

data class Challenge(
    val challName: String,
    val type: ChallengeType,  // Use an enum to represent the type
    val goal: Int,
    var isCompleted: Boolean = false
)

enum class ChallengeType {
    MOVIES_WATCHED,
    CHALLENGES_COMPLETED
}