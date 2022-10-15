package co.edu.uan.android.sample.ejemploapis105.models

import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import co.edu.uan.android.sample.ejemploapis105.views.MainActivity

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
        val values = ContentValues()
        values.put("uid", cat.id)
        values.put("url", cat.url)
        values.put("width", cat.width)
        values.put("height", cat.height)
        db.insert("cats", null, values)
    }

    fun loadCats(): Array<Cat> {
        val cats = mutableListOf<Cat>()
        val cursor = db.rawQuery("SELECT * FROM cats",null)
        with(cursor) {
            while(moveToNext()) {
                val uid = getString(getColumnIndexOrThrow("uid"))
                val url = getString(getColumnIndexOrThrow("url"))
                val width = getInt(getColumnIndexOrThrow("width"))
                val height = getInt(getColumnIndexOrThrow("height"))
                val cat = Cat(uid, url, width, height)
                cats.add(cat)
                activity.addCatImage(cat)
            }
        }
        return cats.toTypedArray()
    }
}