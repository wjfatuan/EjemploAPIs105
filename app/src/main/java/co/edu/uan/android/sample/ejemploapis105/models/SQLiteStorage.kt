package co.edu.uan.android.sample.ejemploapis105.models

import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import co.edu.uan.android.sample.ejemploapis105.MainActivity

class SQLiteStorage(val activity: MainActivity) {

    lateinit var db: SQLiteDatabase

    val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS cats (" +
            "uid VARCHAR(20)," +
            "url VARCHAR(256)," +
            "width INTEGER," +
            "height INTEGER" +
            ")"

    fun createDatabase() {
        db = activity.openOrCreateDatabase("cats_db", MODE_PRIVATE, null)
        db.execSQL(CREATE_TABLE)
    }

    fun addCat(cat: Cat) {
        db.execSQL("INSERT INTO cats(uid, url, width, height) VALUES (" +
                "'${cat.id}'," +
                "'${cat.url}'," +
                "${cat.width}," +
                "${cat.height}" +
                ")")

    }
}