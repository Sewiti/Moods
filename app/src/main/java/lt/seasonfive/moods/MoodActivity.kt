package lt.seasonfive.moods

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.disposables.Disposable
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.databinding.ActivityMoodBinding
import java.util.*

class MoodActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoodBinding
    lateinit var date: String
    lateinit var description: String
    var imageId: Int = 0

    lateinit var database: MoodDatabase
    var disposable: Disposable? = null

    lateinit var mood: Mood

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mood)

        setupDatePicker()

        binding.btnSaveMood.setOnClickListener { view: View ->
//            mood = Mood(
//                uid = 0,
//                date = binding.etDate.text.toString(),
//                description = binding.etDescription.text.toString(),
//                image = imageId
//            )

//            Log.d("MANOMUDASNAHUI", "" + mood)

//            disposable = database
//                .getMoodsDao()
//                .insert(mood)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//
//                }, {
//
//                })


        }

    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.btnBackMood.setOnClickListener{

            val datePicker = DatePickerDialog(this, R.style.MoodsDatePicker, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.etDate.setText("" + year + " - " + month + " - " + dayOfMonth)
            }, year, month, day)

            datePicker.show()
        }
    }

    fun onImageSelect(view: View) {
        when (view.id) {
            R.id.mood_wink -> {
                Toast.makeText(this, "Wink", Toast.LENGTH_SHORT).show()
                view.setOnClickListener {
                    imageId = R.id.mood_wink
                }
            }
            R.id.mood_relaxed -> {
                Toast.makeText(this, "Relaxed", Toast.LENGTH_SHORT).show()
                view.setOnClickListener {
                    imageId = R.id.mood_wink
                }
            }
            R.id.mood_love -> {
                Toast.makeText(this, "Love", Toast.LENGTH_SHORT).show()
                view.setOnClickListener {
                    imageId = R.id.mood_wink
                }
            }
            R.id.mood_desperate -> {
                Toast.makeText(this, "Desperate", Toast.LENGTH_SHORT).show()
                view.setOnClickListener {
                    imageId = R.id.mood_wink
                }
            }
            R.id.mood_poop -> {
                Toast.makeText(this, "Poop", Toast.LENGTH_SHORT).show()
                view.setOnClickListener {
                    imageId = R.id.mood_wink
                }
            }
        }
    }
}