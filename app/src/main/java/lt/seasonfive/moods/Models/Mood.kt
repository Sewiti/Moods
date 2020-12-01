package lt.seasonfive.moods.Models

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mood(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    val date: String?,
    val description: String?,
    val image: Int?
)
