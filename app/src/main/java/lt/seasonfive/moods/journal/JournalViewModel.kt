package lt.seasonfive.moods.journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import lt.seasonfive.moods.database.MoodDao

class JournalViewModel(
    val database: MoodDao,
    application: Application
) : AndroidViewModel(application) {

    val moods = database.getAllMoods()

    private val _navigateToMood = MutableLiveData<Long>()
    val navigateToMood
        get() = _navigateToMood

    fun onMoodClicked(id: Long) {
        _navigateToMood.value = id
    }

    fun doneNavigating() {
        _navigateToMood.value = null
    }
}