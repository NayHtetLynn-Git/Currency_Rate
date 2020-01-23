package com.nayhtetlynn.currencyrate.model

import java.io.Serializable

data class ExchangeRate (val currency:String, val buyRate:String, val sellRate:String):Serializable