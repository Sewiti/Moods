package lt.seasonfive.moods.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods_table")
data class Mood (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "date")
    var date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "mood")
    var moodQuality: Int = -1
)

