package com.nayhtetlynn.currencyrate.model

import java.io.Serializable

data class ExchangeData(val country: String, val unit: String, val rate: String):Serializable