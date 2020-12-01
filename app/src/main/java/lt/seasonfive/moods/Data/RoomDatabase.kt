package lt.seasonfive.moods.Data.Objects

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import lt.seasonfive.moods.Models.Mood
import java.security.AccessControlContext

@Database(entities = arrayOf(Mood::class), version = 1)
abstract class RoomDatabase(): androidx.room.RoomDatabase() {
    abstract fun getMoodsDao(): MoodDao

    companion object {
        private var instance: RoomDatabase? = null

        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                RoomDatabase::class.java, "database.db").build()
            }

            return instance
        }
    }
}