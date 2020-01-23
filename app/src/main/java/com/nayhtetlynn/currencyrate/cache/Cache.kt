package com.nayhtetlynn.currencyrate.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nayhtetlynn.currencyrate.model.Exchange
import com.nayhtetlynn.currencyrate.model.ExchangeRate
import java.io.BufferedReader
import java.io.File

class Cache {

    fun writeJSONtoFileCentral(s:String,exchange: Exchange) {
        //Create a Object of Gson
        val gSon = Gson()

        //Convert the Json object to JsonString
        val jsonString:String = gSon.toJson(exchange)

        //Initialize the File Writer and write into file
        val file= File(s)
        file.writeText(jsonString)
    }

    fun readJSONfromFileCentral(f:String):Exchange {
        //Creating a new Gson object to read data
        val gSon = Gson()

        //Read the PostJSON.json file
        val bufferedReader: BufferedReader = File(f).bufferedReader()

        // Read the text from buffferReader and store in String variable
        val inputString = bufferedReader.use { it.readText() }

        //Convert the Json File to Gson Object
        return gSon.fromJson(inputString, Exchange::class.java)
    }

    fun saveData(context: Context,exchangeList:  MutableList<ExchangeRate>,name:String) {
        val gSon=Gson()
        val json:String=gSon.toJson(exchangeList)
        val sharedPreference: SharedPreferences =context.getSharedPreferences("OtherBank",0)
        val editor=sharedPreference.edit()
        editor.putString(name,json)
        editor.apply()
    }

    fun readExchangeListOtherBank(context: Context,name: String):MutableList<ExchangeRate>{
        val sharedPreferences:SharedPreferences=context.getSharedPreferences("OtherBank",0)
        val json=sharedPreferences.getString(name,"")
        val type=object : TypeToken<MutableList<ExchangeRate>>(){}.type
        return Gson().fromJson(json,type)
    }

    fun checkListOtherBank(context: Context,name: String):Boolean{
        val sharedPreferences:SharedPreferences=context.getSharedPreferences("OtherBank",0)
        val json=sharedPreferences.getString(name,"")
        return json != ""
    }

    fun saveDate(context: Context,date:String,name: String){
        val sharedPreference: SharedPreferences =context.getSharedPreferences("OtherBank",0)
        val editor=sharedPreference.edit()
        editor.putString(name,date)
        editor.apply()
    }

    fun getDate(context: Context,name: String):String{
        val sharedPreferences:SharedPreferences=context.getSharedPreferences("OtherBank",0)
        return sharedPreferences.getString(name,"").toString()
    }
}