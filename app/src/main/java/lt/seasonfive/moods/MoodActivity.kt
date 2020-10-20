package lt.seasonfive.moods

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.databinding.ActivityMoodBinding
import java.util.*

class MoodActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mood)

        setDatePicker()

    }

    private fun setDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.btnBackMood.setOnClickListener{

            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.etDate.setText("" + year + " - " + month + " - " + day)
            }, year, month, day)

            datePicker.show()
        }
    }

    fun onImageSelect(view: View) {
        when (view.id) {
            R.id.mood_wink -> Toast.makeText(this, "Wink", Toast.LENGTH_SHORT).show()
            R.id.mood_relaxed -> Toast.makeText(this, "relaxed", Toast.LENGTH_SHORT).show()
            R.id.mood_love -> Toast.makeText(this, "Love", Toast.LENGTH_SHORT).show()
            R.id.mood_desperate -> Toast.makeText(this, "Desperate", Toast.LENGTH_SHORT).show()
            R.id.mood_poop -> Toast.makeText(this, "Poop", Toast.LENGTH_SHORT).show()
        }
    }
}