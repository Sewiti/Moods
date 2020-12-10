package lt.seasonfive.moods.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.seasonfive.moods.database.MoodDao
import java.util.*

class StatisticsViewModel(
    val database: MoodDao,
    application: Application
) : AndroidViewModel(application) {

    val total = database.getTotalMoods()
    val avgMoodAllTime = database.getAvgMoodAllTime()

    private val _avgMoodLast7D = MutableLiveData<Float>()
    val avgMoodLast7D
        get() = _avgMoodLast7D as LiveData<Float>

    fun onCreate() {
        val cal = Calendar.getInstance()

        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)

        val to = cal.timeInMillis
        val from = to - 604800000L

        viewModelScope.launch {
            _avgMoodLast7D.value = database.getAvg(from, to)
        }
    }

//    val moods = database.getAllMoods()
//
//    private val _navigateToMood = MutableLiveData<Mood>()
//    val navigateToMood
//        get() = _navigateToMood
//
//    fun onMoodClicked(moodId: Long) {
//        viewModelScope.launch {
//            val mood = database.get(moodId)
//            _navigateToMood.value = mood
//        }
//    }
//
//    fun doneNavigating() {
//        _navigateToMood.value = null
//    }
}