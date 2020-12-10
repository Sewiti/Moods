package lt.seasonfive.moods.database

import androidx.lifecycle.LiveData
import androidx.room.*
import lt.seasonfive.moods.database.Mood

@Dao
interface MoodDao {
    @Insert
    suspend fun insert(mood: Mood)

    @Update
    suspend fun update(mood: Mood)

    @Query("SELECT * from moods_table WHERE id = :id")
    suspend fun get(id: Long): Mood?

    @Query("DELETE FROM moods_table WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM moods_table ORDER BY date DESC")
    fun getAllMoods() : LiveData<List<Mood>>

    @Query("SELECT * FROM moods_table WHERE date >= :from AND date < :to ORDER BY date DESC")
    suspend fun getMoodsByDate(from: Long, to: Long) : List<Mood>

    @Query("SELECT COUNT(id) FROM moods_table")
    fun getTotalMoods() : LiveData<Int>

    @Query("SELECT AVG(mood) FROM moods_table ")
    fun getAvgMoodAllTime() : LiveData<Float>

    @Query("SELECT AVG(mood) FROM moods_table WHERE date >= :from AND date < :to")
    suspend fun getAvg(from: Long, to: Long) : Float
}