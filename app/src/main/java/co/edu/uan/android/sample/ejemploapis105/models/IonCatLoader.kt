package co.edu.uan.android.sample.ejemploapis105.models

import android.app.Activity
import android.content.Context
import android.util.Log
import co.edu.uan.android.sample.ejemploapis105.MainActivity
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class IonCatLoader(val context: MainActivity) {

    fun loadCats() {
        Ion.with(context)
            .load("https://api.thecatapi.com/v1/images/search")
            .asString()
            .setCallback(object: FutureCallback<String> {
                override fun onCompleted(e: Exception?, result: String?) {
                    processData(result!!)
                }

            })
    }

    fun processData(data: String) {
        try {
            // extract the information from JSON data
            val json = JSONArray(data)
            val cat = json.get(0) as JSONObject
            val url = cat.getString("url")
            val width = cat.getInt("width")
            val height = cat.getInt("height")
            val catObject = Cat("", url, width, height)
            Log.v("JSONDATA",json.toString())
            context.addCatImage(catObject)

        } catch (e: JSONException) {
            Log.wtf("JSONDATA", e)
        }
    }
}