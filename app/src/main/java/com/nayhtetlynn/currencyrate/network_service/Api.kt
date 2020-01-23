package com.nayhtetlynn.currencyrate.network_service

import com.nayhtetlynn.currencyrate.model.Exchange
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("latest")
    fun getCurrentExchangeRate(): Call<Exchange>

    @GET("currencies")
    fun getCurrencies(): Call<Exchange>

    @GET("history/{date}")
    fun getCustomExchangeRate(@Path("date") url:String): Call<Exchange>
}