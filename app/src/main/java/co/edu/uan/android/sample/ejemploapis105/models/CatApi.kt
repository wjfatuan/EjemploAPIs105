package co.edu.uan.android.sample.ejemploapis105.models

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    //GET https://api.thecatapi.com/v1/images/search?limit=num
    @GET("/v1/images/search")
    fun searchCats(@Query("limit") num: Int): Call<Array<Cat>>

    companion object {
        fun getInstance(): CatApi {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var catsApi = retrofit.create(CatApi::class.java)
            return catsApi
        }

    }
}