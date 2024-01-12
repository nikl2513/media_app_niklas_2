package com.example.mediaappniklas2.uiLayer.challenges

import androidx.lifecycle.ViewModel

class ChallengesViewModel: ViewModel(){
    private var  _moviesWatched : Int = 0;
    private var _challengesCompleted : Int = 0;
    private val _challengeList : List<Challenge> = emptyList()
    val moviesWatched: Int
        get() = _moviesWatched

    val getchallengesCompleted: Int
        get() = _challengesCompleted

    val challengeList : List<Challenge>
        get() = _challengeList
    fun watchedMovie(){
        _moviesWatched.inc()

    }

    fun challengeCompleted(){
        _challengesCompleted.inc()
    }
    private fun checkChallengeCompletion(challengeType: ChallengeType) {
        val matchingChallenge = challengeList.find { it.type == challengeType && it.goal == getCount(challengeType) }
        matchingChallenge?.isCompleted = true
    }

    private fun getCount(challengeType: ChallengeType): Int {
        return when (challengeType) {
            ChallengeType.MOVIES_WATCHED -> _moviesWatched
            ChallengeType.CHALLENGES_COMPLETED -> _challengesCompleted
        }
    }

    fun createList() : List<Challenge>{
return listOf(
    Challenge("Watch 5 movies", ChallengeType.MOVIES_WATCHED, 5),
    Challenge("Watch 15 movies",ChallengeType.MOVIES_WATCHED,15),
    Challenge("Complete 3 challenges", ChallengeType.CHALLENGES_COMPLETED, 3),

)

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