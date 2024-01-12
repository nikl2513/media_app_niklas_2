package com.example.mediaappniklas2.uiLayer.challenges

import WatchedHistoryManager
import androidx.lifecycle.ViewModel

class ChallengesViewModel(watchedHistoryManager: WatchedHistoryManager) : ViewModel() {
    private var _moviesWatched: Int = 0;
    private var _challengesCompleted: Int = 0;
    private val _challengeList: MutableList<Challenge> = mutableListOf()


    val moviesWatched: Int
        get() = _moviesWatched

    val getchallengesCompleted: Int
        get() = _challengesCompleted

    val challengeList: List<Challenge>
        get() = _challengeList


    fun challengeCompleted() {
        _challengesCompleted.inc()
    }

    private fun checkUncompletedChallenges() {
        challengeList.filter { !it.isCompleted }.forEach { challenge ->
            val challengeGoal = when (challenge.type) {
                ChallengeType.MOVIES_WATCHED -> challenge.goal
                ChallengeType.CHALLENGES_COMPLETED -> _challengesCompleted
            }

            if (getCount(challenge.type) >= challengeGoal) {
                challenge.isCompleted = true
                // Handle the completion logic here, such as showing a message or triggering further actions.
            }
        }
    }

    private fun getCount(challengeType: ChallengeType): Int {
        return when (challengeType) {
            ChallengeType.MOVIES_WATCHED -> _moviesWatched
            ChallengeType.CHALLENGES_COMPLETED -> _challengesCompleted
        }
    }

    fun createList() {
        _challengeList.add(Challenge("Watch 5 movies", ChallengeType.MOVIES_WATCHED, 5))
        _challengeList.add(Challenge("Watch 15 movies", ChallengeType.MOVIES_WATCHED, 15))
        _challengeList.add(Challenge("Complete 3 challenges", ChallengeType.CHALLENGES_COMPLETED, 3)
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