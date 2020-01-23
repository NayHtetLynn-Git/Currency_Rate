package com.nayhtetlynn.currencyrate.network_service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl="http://forex.cbm.gov.mm/api/"

    val instances: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory
                .create(GsonBuilder()
                .setLenient()
                .create()))
            .build()

        retrofit.create(Api::class.java)
    }
}