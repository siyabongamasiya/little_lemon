package android.capstone.littlelemon.Repos

import android.capstone.littlelemon.data.itemRoom
import android.capstone.littlelemon.utils.itemDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [itemRoom :: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
   abstract fun itemDao(): itemDao
    companion object {
        @Volatile
      private var INSTANCE: AppDatabase? = null

         fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
             if (tempInstance != null) {
         return tempInstance
                 }
            synchronized(this) {
                 val instance = Room.databaseBuilder(
              context.applicationContext,
               AppDatabase::class.java,
                 "app_database"
                ).build()
                 INSTANCE = instance
                 return instance
                 }
            }
        }
}