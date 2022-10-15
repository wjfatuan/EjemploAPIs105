package co.edu.uan.android.sample.ejemploapis105.models

import android.util.Log
import co.edu.uan.android.sample.ejemploapis105.models.apis.CatApi
import co.edu.uan.android.sample.ejemploapis105.views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCatLoader(val context: MainActivity, val storage: RoomStorage) {

    fun loadCats(num: Int) {
        var catsApi = CatApi.getInstance()
        var search: Call<Array<Cat>> = catsApi.searchCats(num)
        search.enqueue(object: Callback<Array<Cat>> {
            override fun onResponse(call: Call<Array<Cat>>, response: Response<Array<Cat>>) {
                for(cat in response.body()!!.asList()) {
                    storage.addCat(cat)
                    context.addCatImage(cat)
                }
            }
            override fun onFailure(call: Call<Array<Cat>>, t: Throwable) {
                Log.e("JSONDATA", t.message, t)
            }
        })
    }
}