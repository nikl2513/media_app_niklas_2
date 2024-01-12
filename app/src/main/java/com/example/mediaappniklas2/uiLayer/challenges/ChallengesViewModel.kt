package com.example.mediaappniklas2.uiLayer.challenges

import WatchedHistoryManager
import androidx.lifecycle.ViewModel

class ChallengesViewModel(watchedHistoryManager: WatchedHistoryManager) : ViewModel() {
    private var _moviesWatched: Int = 0
    private var _challengesCompleted: Int = 0
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
    fun addchallengecompleted(){
        _challengesCompleted++
    }

     fun checkUncompletedChallenges() {
        _challengeList.filter { !it.isCompleted }.forEach { challenge ->
            val count = getCount(challenge.type)

            if (count >= challenge.goal) {
                challenge.isCompleted = true
                // Handle the completion logic here, such as showing a message or triggering further actions.
                updateChallenges()
            }
        }
    }

    private fun getCount(challengeType: ChallengeType): Int {
        return when (challengeType) {
            ChallengeType.MOVIES_WATCHED -> _moviesWatched
            ChallengeType.CHALLENGES_COMPLETED -> _challengesCompleted
        }
    }

    fun updateChallenges() {
        // Replace completed challenges with new goals
        _challengeList.forEach { challenge ->
            if (challenge.isCompleted) {
                challenge.isCompleted = false
                challenge.goal = calculateNewGoal()
            }
        }

        // Remove challenges with a goal of 0
        _challengeList.removeAll { it.goal == 0 }

        // Optionally, you can sort the list based on some criteria
        _challengeList.sortBy { it.goal }
    }


    fun calculateNewGoal(): Int {
        // Implement your logic to calculate the new goal here
        // For example, you can find the maximum goal in the existing challenges and add 5
        val maxGoal = _challengeList.maxByOrNull { it.goal }?.goal ?: 0
        return maxGoal + 5
    }

    fun createList() {
        _challengeList.add(Challenge("Watch 5 movies", ChallengeType.MOVIES_WATCHED, 5))
        _challengeList.add(Challenge("Watch 15 movies", ChallengeType.MOVIES_WATCHED, 15))
        _challengeList.add(Challenge("Complete 3 challenges", ChallengeType.CHALLENGES_COMPLETED, 3))
    }

}

data class Challenge(
    val challName: String,
    val type: ChallengeType,  // Use an enum to represent the type
    var goal: Int,
    var isCompleted: Boolean = false
)

enum class ChallengeType {
    MOVIES_WATCHED,
    CHALLENGES_COMPLETED
}