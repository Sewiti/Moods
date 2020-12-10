package lt.seasonfive.moods.calendar

import android.app.Application
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.database.MoodDao
import java.util.*


class CalendarViewModel(
    val database: MoodDao,
    application: Application
) : AndroidViewModel(application) {

    private val _cal = Calendar.getInstance()


    private val _moods = MutableLiveData<List<Mood>>()
    val moods
        get() = _moods as LiveData<List<Mood>>


    private val _navigateToMood = MutableLiveData<Mood>()
    val navigateToMood
        get() = _navigateToMood as LiveData<Mood>


    fun onCreate() {
        _cal.set(Calendar.HOUR, 0)
        _cal.set(Calendar.MINUTE, 0)
        _cal.set(Calendar.MILLISECOND, 0)

        val to = _cal.timeInMillis
        val from = to - 86400000L

        viewModelScope.launch {
            _moods.value = database.getMoodsByDate(from, to)
        }
    }


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


    fun onDateSelected(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        _cal.set(Calendar.YEAR, year)
        _cal.set(Calendar.MONTH, month)
        _cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val to = _cal.timeInMillis
        val from = to - 86400000L

        viewModelScope.launch {
            _moods.value = database.getMoodsByDate(from, to)
        }
    }
}