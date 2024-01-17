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




    fun checkUncompletedChallenges() {
        _moviesWatched = watchedHistoryManager.getWatchedHistoryList().size

        _challengeList.filter { !it.isCompleted }.forEach { challenge ->
            val count = getCount(challenge.type)

            if (count >= challenge.goal) {
                challenge.isCompleted = true

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

        _challengeList.filter { it.isCompleted }.forEach { challenge ->
            challenge.goal = calculateNewGoal()
            challenge.isCompleted = false
        }

        // Add new challenges
        _challengeList.add(Challenge("Watch ${calculateNewGoal()} Movies", ChallengeType.MOVIES_WATCHED, calculateNewGoal()))


        _challengeList.removeAll { it.goal == 0 }


        _challengeList.sortBy { it.goal }


        _challengeListState.value = _challengeList.toList()
    }






    fun calculateNewGoal(): Int {

        val maxGoal = _moviesWatched +(5-(_moviesWatched%5))/*_challengeList.maxByOrNull { it.goal }?.goal ?: 0*/
        return maxGoal
    }

    fun createList() {
        _challengeList.add(Challenge("watch " + calculateNewGoal() + " movies", ChallengeType.MOVIES_WATCHED, calculateNewGoal()))
        }
    fun calculateProgress(goal: Int): Int {
        val watchedMovies = watchedHistoryManager.getWatchedHistoryList().size
        return if (goal > 0) {

            ((watchedMovies.toFloat() / goal) * 100).coerceIn(0f, 100f).toInt()
        } else {
            0
        }
    }

}

data class Challenge(
    val challName: String,
    val type: ChallengeType, 
    var goal: Int,
    var isCompleted: Boolean = false
)

enum class ChallengeType {
    MOVIES_WATCHED,
    CHALLENGES_COMPLETED
}