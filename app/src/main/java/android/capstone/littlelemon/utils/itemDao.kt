package android.capstone.littlelemon.utils

import android.capstone.littlelemon.data.itemRoom
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface itemDao {
    @Insert
     suspend fun insert(item : itemRoom)

    @Query("SELECT * FROM items")
    fun getAll(): LiveData<List<itemRoom>>

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}