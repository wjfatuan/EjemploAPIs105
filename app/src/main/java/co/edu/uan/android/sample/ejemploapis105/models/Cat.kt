package co.edu.uan.android.sample.ejemploapis105.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats_table")
data class Cat(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
) {
}