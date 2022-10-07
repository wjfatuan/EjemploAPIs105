package co.edu.uan.android.sample.ejemploapis105.models

import android.app.Activity
import android.content.Context
import android.util.Log
import co.edu.uan.android.sample.ejemploapis105.MainActivity
import com.google.gson.Gson
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class IonCatLoader(val context: MainActivity) {

    fun loadCats(num: Int) {
        Ion.with(context)
            .load("https://api.thecatapi.com/v1/images/search?limit="+num)
            .asString()
            .setCallback(object: FutureCallback<String> {
                override fun onCompleted(e: Exception?, result: String?) {
                    processData(result!!)
                }

            })
    }

    fun processData(data: String) {
        try {
            val gson = Gson()
            val catResponse = gson.fromJson(data, Array<Cat>::class.java)
            Log.v("JSONDATA",data)
            for(cat in catResponse)
                context.addCatImage(cat)

        } catch (e: JSONException) {
            Log.wtf("JSONDATA", e)
        }
    }
}