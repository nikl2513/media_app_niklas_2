import android.content.Context
import android.content.SharedPreferences
import com.example.mediaappniklas2.datalayer.MovieData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WatchedHistoryManager private constructor(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("watched_history_prefs", Context.MODE_PRIVATE)
    private val watchedHistoryKey = "watched_history_list"

    companion object {
        @Volatile
        private var instance: WatchedHistoryManager? = null

        fun getInstance(context: Context): WatchedHistoryManager {
            return instance ?: synchronized(this) {
                instance ?: WatchedHistoryManager(context).also { instance = it }
            }
        }
    }

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
    fun removeFromWatchedHistory(mediaItem: MovieData){
        val currentList = getWatchedHistoryList().toMutableList()
        if (currentList.contains(mediaItem)){
            currentList.remove(mediaItem)
            saveWatchedHistoryList(currentList)
        }
    }

    private fun saveWatchedHistoryList(list: List<MovieData>) {
        val jsonString = Json.encodeToString(list)
        preferences.edit().putString(watchedHistoryKey, jsonString).apply()
    }
}
