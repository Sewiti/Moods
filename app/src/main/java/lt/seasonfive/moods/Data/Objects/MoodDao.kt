package lt.seasonfive.moods.Data.Objects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import lt.seasonfive.moods.Models.Mood

@Dao
interface MoodDao {
    @Insert
    fun insert(mood: Mood) : Single<Long>

//    @Delete
//    fun delete(mood: Mood) : Completable

    @Query("SELECT * FROM mood")
    fun getAllMoods() : Single<List<Mood>>
}