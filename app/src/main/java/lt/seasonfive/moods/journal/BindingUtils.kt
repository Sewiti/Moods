package lt.seasonfive.moods.journal

import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.R

@BindingAdapter("moodQualityImage")
fun ImageView.setSleepImage(item: Mood?) {
    item?.let {
        setImageResource(when (item.moodQuality) {
            0 -> R.drawable.mood_wink
            1 -> R.drawable.mood_love
            2 -> R.drawable.mood_relaxed
            3 -> R.drawable.mood_desperate
            4 -> R.drawable.mood_poop
            else -> R.drawable.mood_poop
        })
    }
}

@BindingAdapter("moodDate")
fun TextView.setSleepQualityString(item: Mood?) {
    item?.let {
        text = SimpleDateFormat.getDateInstance().format(item.date)
    }
}
