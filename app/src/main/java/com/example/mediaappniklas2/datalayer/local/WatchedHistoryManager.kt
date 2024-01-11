package com.example.mediaappniklas2.datalayer.local
import android.content.Context
import com.example.mediaappniklas2.datalayer.MovieData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
class WatchedHistoryManager(context: Context) {
    private val preferences = context.getSharedPreferences("watched_history_prefs", Context.MODE_PRIVATE)
    private val watchedHistoryKey = "watched_history_list"

    fun getWatchedHistoryList(): List<MovieData> {
        val jsonString = preferences.getString(watchedHistoryKey, null)
        return if (jsonString != null) {
            try {
                Json.decodeFromString(jsonString)
            } catch (error: Throwable) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun addToWatchedHistory(mediaItem: MovieData) {
        val currentList = getWatchedHistoryList().toMutableList()
        if (!currentList.contains(mediaItem)) {
            currentList.add(mediaItem)
            saveWatchedHistoryList(currentList)
        }
    }

    private fun saveWatchedHistoryList(list: List<MovieData>) {
        val jsonString = Json.encodeToString(list)
        preferences.edit().putString(watchedHistoryKey, jsonString).apply()
    }
}
