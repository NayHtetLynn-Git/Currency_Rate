package com.nayhtetlynn.currencyrate.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("info") val info: String,
    @SerializedName("description") val description: String,
    @SerializedName("timestamp") val timestamp:Int,
    @SerializedName("rates") val rates: JsonObject,
    @SerializedName("currencies") val currencies:JsonObject
)