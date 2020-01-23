package com.nayhtetlynn.currencyrate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.repository.CurrenciesRepository

class CurrenciesViewModel:ViewModel() {
    private val currenciesRepository=CurrenciesRepository()

    fun getCurrencies():LiveData<Exchange>{
        return currenciesRepository.getCurrencies()
    }
}