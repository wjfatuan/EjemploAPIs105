package co.edu.uan.android.sample.ejemploapis105.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import co.edu.uan.android.sample.ejemploapis105.databinding.ActivityMainBinding
import co.edu.uan.android.sample.ejemploapis105.models.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var loader: RetrofitCatLoader
    lateinit var storage: RoomStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        storage = RoomStorage(this)
        loader = RetrofitCatLoader(this,storage)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLoadCats.setOnClickListener {
            loader.loadCats(binding.edtCatNumber.text.toString().toInt())
        }
        storage.createDatabase()
        storage.loadCats()
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