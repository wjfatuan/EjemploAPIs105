package co.edu.uan.android.sample.ejemploapis105.models

import android.util.Log
import co.edu.uan.android.sample.ejemploapis105.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCatLoader(val context: MainActivity) {

    fun loadCats(num: Int) {
        var catsApi = CatApi.getInstance()
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