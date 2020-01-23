package com.nayhtetlynn.currencyrate.repository

import androidx.lifecycle.MutableLiveData
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.network_service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomExchangeRateRepository {
    fun getCustomExchangeRate(date:String):MutableLiveData<Exchange>{
        val exchangeRateList=MutableLiveData<Exchange>()

        val api=RetrofitClient.instances.getCustomExchangeRate(date)

        api.enqueue(object : Callback<Exchange>{
            override fun onFailure(call: Call<Exchange>, t: Throwable) {
                exchangeRateList.value=null
            }

            override fun onResponse(call: Call<Exchange>, response: Response<Exchange>) {
                if (response.isSuccessful){
                    exchangeRateList.value=response.body()
                }else{
                    exchangeRateList.value=null
                }
            }

        })
        return exchangeRateList
    }
}