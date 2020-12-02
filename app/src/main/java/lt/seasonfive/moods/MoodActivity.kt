package lt.seasonfive.moods

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.database.MoodDao
import lt.seasonfive.moods.databinding.ActivityMoodBinding
import java.util.*

class MoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoodBinding
    private lateinit var database: MoodDao

    private val cal = Calendar.getInstance()
    private var moodQuality: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mood)
        database = MoodDatabase.getInstance(application).moodDatabaseDao

        setupDatePicker()

        val id = intent?.extras?.getLong("id", -1)
        if (id != null && id >= 0L) {
            cal.timeInMillis = intent?.extras?.getLong("date")!!
            binding.etDescription.setText(intent?.extras?.getString("description")!!)
            moodQuality = intent?.extras?.getInt("moodQuality")!!
        }

        updateDateText()

        binding.btnSaveMood.setOnClickListener { view: View ->
            if (moodQuality == -1) {
                Toast.makeText(view.context, "You must select a mood", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()

            intent.putExtra("id", id)
            intent.putExtra("date", cal.timeInMillis)
            intent.putExtra("description", binding.etDescription.text.toString())
            intent.putExtra("moodQuality", moodQuality)

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setupDatePicker() {
//        cal.set(Calendar.HOUR, 0)
//        cal.set(Calendar.MINUTE, 0)
//        cal.set(Calendar.MILLISECOND, 0)
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        binding.btnBackMood.setOnClickListener {
            DatePickerDialog(
                this, R.style.MoodsDatePicker,
                { view, year, month, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    updateDateText()
                }, year, month, day
            ).show()
        }
    }

    private fun updateDateText() {
        val text = SimpleDateFormat.getDateInstance().format(cal.timeInMillis)
        binding.etDate.setText(text)
    }

    fun onImageSelect(view: View) {
        when (view.id) {
            R.id.mood_wink -> {
                moodQuality = 0
            }
            R.id.mood_relaxed -> {
                moodQuality = 1
            }
            R.id.mood_love -> {
                moodQuality = 2
            }
            R.id.mood_desperate -> {
                moodQuality = 3
            }
            R.id.mood_poop -> {
                moodQuality = 4
            }
        }
    }
}