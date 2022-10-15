package co.edu.uan.android.sample.ejemploapis105.models.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.uan.android.sample.ejemploapis105.models.Cat

@Database(entities = arrayOf(Cat::class), version = 1)
abstract class CatsDatabase: RoomDatabase() {
    abstract fun catDao() : CatDao
}