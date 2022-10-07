package co.edu.uan.android.sample.ejemploapis105.models

import android.util.Log
import co.edu.uan.android.sample.ejemploapis105.MainActivity
import com.google.gson.Gson
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class RetrofitCatLoader(val context: MainActivity) {

    fun loadCats(num: Int) {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var catsApi = retrofit.create(CatApi::class.java)
        var search: Call<Array<Cat>> = catsApi.searchCats(num)
        search.enqueue(object: Callback<Array<Cat>> {
            override fun onResponse(call: Call<Array<Cat>>, response: Response<Array<Cat>>) {
                for(cat in response.body()!!.asList())
                    context.addCatImage(cat)
            }
            override fun onFailure(call: Call<Array<Cat>>, t: Throwable) {
                Log.e("JSONDATA", t.message, t)
            }
        })
    }
}