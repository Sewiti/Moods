package lt.seasonfive.moods.database

import androidx.lifecycle.LiveData
import androidx.room.*
import lt.seasonfive.moods.database.Mood

@Dao
interface MoodDao {
    @Insert
    fun insert(mood: Mood)

    @Update
    fun update(mood: Mood)

    @Query("SELECT * from moods_table WHERE id = :id")
    fun get(id: Long): Mood?

    @Query("DELETE FROM moods_table WHERE id = :id")
    fun delete(id: Long)

    @Query("SELECT * FROM moods_table ORDER BY date DESC")
    fun getAllMoods() : LiveData<List<Mood>>
}