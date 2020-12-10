package lt.seasonfive.moods.journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.database.MoodDao

class JournalViewModel(
    val database: MoodDao,
    application: Application
) : AndroidViewModel(application) {

    val moods = database.getAllMoods()

    private val _navigateToMood = MutableLiveData<Mood>()
    val navigateToMood
        get() = _navigateToMood

    fun onMoodClicked(moodId: Long) {
        viewModelScope.launch {
            val mood = database.get(moodId)
            _navigateToMood.value = mood
        }
    }

    fun deleteMood(moodId: Long) {
        viewModelScope.launch {
            database.delete(moodId)
        }
    }

    fun doneNavigating() {
        _navigateToMood.value = null
    }
}