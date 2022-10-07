package co.edu.uan.android.sample.ejemploapis105.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    //GET https://api.thecatapi.com/v1/images/search?limit=num
    @GET("/v1/images/search")
    fun searchCats(@Query("limit") num: Int): Call<Array<Cat>>
}