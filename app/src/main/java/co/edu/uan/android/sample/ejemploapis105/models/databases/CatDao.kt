package co.edu.uan.android.sample.ejemploapis105.models.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.uan.android.sample.ejemploapis105.models.Cat

@Dao
interface CatDao {
    // create
    @Insert
    fun createCat(vararg cat: Cat)
    // read
    @Query("SELECT * FROM cats_table")
    fun readAll(): List<Cat>
    // update
    // delete
}