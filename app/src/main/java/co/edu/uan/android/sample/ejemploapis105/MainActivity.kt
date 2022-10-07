package co.edu.uan.android.sample.ejemploapis105

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import co.edu.uan.android.sample.ejemploapis105.databinding.ActivityMainBinding
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLoadCats.setOnClickListener {
            loadCats()
        }

    }

    fun loadCats() {
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
            // extract the information from JSON data
            val json = JSONArray(data)
            val cat = json.get(0) as JSONObject
            val url = cat.getString("url")
            val width = cat.getInt("width")
            val height = cat.getInt("height")
            Log.v("JSONDATA",json.toString())
            addCatImage(url)

        } catch (e: JSONException) {
            Log.wtf("JSONDATA", e)
        }
    }

    fun addCatImage(url: String) {
        val container = binding.catsContainer
        val iv = ImageView(this)
        // use picasso to load the image
        Picasso
            .get()
            .load(url)
            .into(iv)
        container.addView(iv)
    }

}