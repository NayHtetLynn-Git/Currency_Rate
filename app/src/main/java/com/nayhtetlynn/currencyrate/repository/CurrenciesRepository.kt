package com.nayhtetlynn.currencyrate.repository

import androidx.lifecycle.MutableLiveData
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.network_service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrenciesRepository {
    fun getCurrencies():MutableLiveData<Exchange>{
        val currenciesList=MutableLiveData<Exchange>()
        val api=RetrofitClient.instances.getCurrencies()

        api.enqueue(object : Callback<Exchange>{
            override fun onFailure(call: Call<Exchange>, t: Throwable) {
                currenciesList.value=null
            }

            override fun onResponse(call: Call<Exchange>, response: Response<Exchange>) {
                if (response.isSuccessful){
                    currenciesList.value=response.body()
                }else{
                    currenciesList.value=null
                }
            }

        })
        return currenciesList
    }
}