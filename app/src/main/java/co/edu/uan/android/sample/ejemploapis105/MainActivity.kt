package co.edu.uan.android.sample.ejemploapis105

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import co.edu.uan.android.sample.ejemploapis105.databinding.ActivityMainBinding
import co.edu.uan.android.sample.ejemploapis105.models.Cat
import co.edu.uan.android.sample.ejemploapis105.models.IonCatLoader
import co.edu.uan.android.sample.ejemploapis105.models.RetrofitCatLoader
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
    lateinit var loader: RetrofitCatLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        loader = RetrofitCatLoader(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLoadCats.setOnClickListener {
            loader.loadCats(binding.edtCatNumber.text.toString().toInt())
        }
    }
    fun addCatImage(cat: Cat) {
        val container = binding.catsContainer
        val iv = ImageView(this)
        // use picasso to load the image
        Picasso
            .get()
            .load(cat.url)
            .into(iv)
        container.addView(iv)
    }

}