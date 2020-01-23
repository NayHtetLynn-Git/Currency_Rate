package com.nayhtetlynn.currencyrate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.repository.CustomExchangeRateRepository

class CustomExchangeRateViewModel:ViewModel() {
    private val customExchangeRateRepository=CustomExchangeRateRepository()

    fun getCustomExchangeRate(date:String):LiveData<Exchange>{
        return customExchangeRateRepository.getCustomExchangeRate(date)
    }
}