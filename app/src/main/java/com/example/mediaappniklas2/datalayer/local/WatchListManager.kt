package com.example.mediaappniklas2.datalayer.local

import android.content.Context
import com.example.mediaappniklas2.datalayer.MovieData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class WatchListManager (context : Context) {
    private val preferences = context.getSharedPreferences("watch_later_prefs", Context.MODE_PRIVATE)
        private val watchLaterKey = "watch_later_list"

    fun getWatchLaterList(): List<MovieData> {
        val jsonString = preferences.getString(watchLaterKey, null)
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

    fun addToWatchLater(mediaItem: MovieData) {
        val currentList = getWatchLaterList().toMutableList()
        if (!currentList.contains(mediaItem)) {
            currentList.add(mediaItem)
            saveWatchLaterList(currentList)
        }
    }

    fun removeFromWatchLater(mediaItem: MovieData) {
        val currentList = getWatchLaterList().toMutableList()
        currentList.remove(mediaItem)
        saveWatchLaterList(currentList)
    }

    private fun saveWatchLaterList(list: List<MovieData>) {
        val jsonString = Json.encodeToString(list)
        preferences.edit().putString(watchLaterKey, jsonString).apply()
    }
}
