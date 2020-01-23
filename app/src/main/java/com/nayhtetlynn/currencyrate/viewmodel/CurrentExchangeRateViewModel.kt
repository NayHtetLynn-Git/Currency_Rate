package com.nayhtetlynn.currencyrate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.repository.CurrentExchangeRateRepository

class CurrentExchangeRateViewModel:ViewModel() {
    private val currentExchangeRateRepository=CurrentExchangeRateRepository()

    fun getCurrentExchangeRate():LiveData<Exchange>{
        return currentExchangeRateRepository.getCurrentExchangeRate()
    }
}