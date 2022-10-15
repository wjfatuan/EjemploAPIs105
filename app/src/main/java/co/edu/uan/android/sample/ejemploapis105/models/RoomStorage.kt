package co.edu.uan.android.sample.ejemploapis105.models

import android.os.AsyncTask
import androidx.room.Room
import co.edu.uan.android.sample.ejemploapis105.models.databases.CatsDatabase
import co.edu.uan.android.sample.ejemploapis105.views.MainActivity

class RoomStorage(val activity: MainActivity) {

    lateinit var db: CatsDatabase

    fun createDatabase() {
        db = Room.databaseBuilder(activity,
            CatsDatabase::class.java, "cats_room_db"
        ).build()

    }

    fun addCat(cat: Cat) {
        object : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg p0: Void?): Boolean {
                db.catDao().createCat(cat)
                return true
            }
        }.execute()
    }

    fun loadCats(): Array<Cat> {
        object: AsyncTask<Void, Void, List<Cat>>() {
            override fun doInBackground(vararg p0: Void?): List<Cat> {
                val cats = db.catDao().readAll()
                return cats
            }

            override fun onPostExecute(result: List<Cat>?) {
                super.onPostExecute(result)
                for( cat in result!!) {
                    activity.addCatImage(cat)
                }
            }

        }
        return listOf<Cat>().toTypedArray()
    }
}