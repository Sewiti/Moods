package lt.seasonfive.moods

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.databinding.ActivityMoodBinding

class MoodActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mood)

        binding.svMoods

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