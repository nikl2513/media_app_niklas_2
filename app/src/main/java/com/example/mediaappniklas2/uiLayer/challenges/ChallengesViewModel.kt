package com.example.mediaappniklas2.uiLayer.challenges

import WatchedHistoryManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChallengesViewModel(private val watchedHistoryManager: WatchedHistoryManager) : ViewModel() {
    private var _moviesWatched: Int = 0
    private var _challengesCompleted: Int = 0
    private val _challengeList: MutableList<Challenge> = mutableListOf()
    // Use MutableStateFlow for challengeList
    private val _challengeListState = MutableStateFlow<List<Challenge>>(emptyList())

    val challengeListState = _challengeListState.asStateFlow()

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
        _moviesWatched = watchedHistoryManager.getWatchedHistoryList().size

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
        // Update completed challenges with new goals
        _challengeList.filter { it.isCompleted }.forEach { challenge ->
            challenge.goal = calculateNewGoal()
            challenge.isCompleted = false
        }

        // Add new challenges
        _challengeList.add(Challenge("Watch ${calculateNewGoal()} Movies", ChallengeType.MOVIES_WATCHED, calculateNewGoal()))

        // Remove challenges with a goal of 0 (completed challenges)
        _challengeList.removeAll { it.goal == 0 }

        // Optionally, you can sort the list based on some criteria
        _challengeList.sortBy { it.goal }

        // Update the StateFlow
        _challengeListState.value = _challengeList.toList()
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

    }
    fun calculateProgress(goal: Int): Int {
        val watchedMovies = watchedHistoryManager.getWatchedHistoryList().size
        return if (goal > 0) {
            // Ensure the progress is between 0 and 100
            ((watchedMovies.toFloat() / goal) * 100).coerceIn(0f, 100f).toInt()
        } else {
            0
        }
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