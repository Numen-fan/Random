package com.jiajia.random.sunnyweather.logic.network

import com.jiajia.random.RandomApplication
import com.jiajia.random.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${RandomApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse> // url中需要query参数，因此，通过参数的形式传递

}