package co.edu.uan.android.sample.ejemploapis105

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    val jsonData = "{\n" +
            "  \"private\": \"true\",              " +
            "  \"from\": \"Alice (alice@ex.com)\", " +
            "  \"subject\": \"Today's event\",     " +
            "  \"to\": [                          " +
            "    \"Robert (roberto@ex.com)\",     " +
            "    \"Charles (cdodd@ex.com)\"       " +
            "  ],\n" +
            "  \"message\": {                     " +
            "    \"lang\": \"english\",           " +
            "    \"text\": \"Call this weekend!\" " +
            "  }\n" +
            "}\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Ion.with(this)
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
            // [{"id":"b2f","url":"https://cdn2.thecatapi.com/images/b2f.jpg","width":500,"height":375}]
            // extract the information from JSON data
            val json = JSONArray(data)
            val cat = json.get(0) as JSONObject
            val id = cat.getString("id")
            val url = cat.getString("url")
            val width = cat.getInt("width")
            val height = cat.getInt("height")
            Log.v("JSONDATA",json.toString())

        } catch (e: JSONException) {
            Log.wtf("JSONDATA", e)
        }
    }

}