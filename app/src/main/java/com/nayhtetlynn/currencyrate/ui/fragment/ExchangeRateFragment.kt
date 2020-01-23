package com.nayhtetlynn.currencyrate.ui.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nayhtetlynn.currencyrate.R
import com.nayhtetlynn.currencyrate.adapter.ExchangeAdapter
import com.nayhtetlynn.currencyrate.cache.Cache

import com.nayhtetlynn.currencyrate.model.ExchangeData
import com.nayhtetlynn.currencyrate.network_receiver.ConnectivityReceiver
import com.nayhtetlynn.currencyrate.ui.activity.CurrencyCalculateActivity
import com.nayhtetlynn.currencyrate.viewmodel.CurrenciesViewModel
import com.nayhtetlynn.currencyrate.viewmodel.CurrentExchangeRateViewModel
import kotlinx.android.synthetic.main.fragment_exchange_rate.*
import org.json.JSONObject
import java.io.File
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class ExchangeRateFragment : Fragment(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var currentExchangeRateViewModel: CurrentExchangeRateViewModel
    private lateinit var currenciesViewModel: CurrenciesViewModel

    private val exchangeList = mutableListOf<ExchangeData>()

    private var rateFile:String?=null
    private var currencyFile:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rateFile=activity?.cacheDir?.absolutePath+"/RateJson.json"
        currencyFile=activity?.cacheDir?.absolutePath+"/PostJson.json"

        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        activity?.registerReceiver(ConnectivityReceiver(),filter)

//        loadReal(rateFile!!, currencyFile!!)

    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (exchangeList.size<=0) {
            if (isConnected) {
                loadReal(rateFile!!, currencyFile!!)
            } else {
                loadCache(rateFile!!, currencyFile!!)
            }
        }
    }

    private fun loadReal(rateFile: String,currencyFile: String){
        currentExchangeRateViewModel =
            ViewModelProviders.of(this)
                .get(CurrentExchangeRateViewModel::class.java)
        currentExchangeRateViewModel.getCurrentExchangeRate()
            .observe(this, Observer { rate ->
                if (rate != null) {

                    Cache().writeJSONtoFileCentral(rateFile, rate)

                    currenciesViewModel = ViewModelProviders.of(this)
                        .get(CurrenciesViewModel::class.java)
                    currenciesViewModel.getCurrencies()
                        .observe(this, Observer {
                            if (it != null) {

                                showDate(rate.timestamp.toLong())

                                Cache().writeJSONtoFileCentral(currencyFile, it)

                                val rateObject = JSONObject(rate.rates.toString())
                                val currencyObject = JSONObject(it.currencies.toString())

                                matchData(rateObject,currencyObject)

                            } else {
                                Log.d("LogDataErrorCurrencies", "Null Value")
                            }
                        })

                } else {
                    Log.d("LogDataErrorRates", "Null Value")
                }
            })
    }

    private fun loadCache(rateFile:String,currencyFile:String){
        if (!File(rateFile).exists() || !File(currencyFile).exists()){
            return
        }
        val rateObject = JSONObject(Cache().readJSONfromFileCentral(rateFile).rates.toString())
        val currencyObject = JSONObject(Cache().readJSONfromFileCentral(currencyFile).currencies.toString())

        showDate(Cache().readJSONfromFileCentral(rateFile).timestamp.toLong())

        matchData(rateObject,currencyObject)

    }

    private fun showDate(timestamp: Long) {
        val date = Date(timestamp * 1000)
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm aaa",Locale.US)
        updatedDate.text=format.format(date)
    }


    private fun matchData(rateObject: JSONObject, currencyObject: JSONObject) {
        exchangeList.clear()
        rateObject.keys().forEach { key ->

            val currentRate = if (key == "JPY" || key == "KHR"
                || key == "IDR" || key == "KRW"
                || key == "LAK" || key == "VND"
            ) "%.4f".format(rateObject.getString(key).replace(",", "").toDouble() / 100
            ) else rateObject.getString(key)
            exchangeList.add(
                ExchangeData(
                    currencyObject.getString(key),
                    key,
                    currentRate
                )
            )
        }

        showExchangeData(exchangeList)
    }

    private fun showExchangeData(exchangeList: MutableList<ExchangeData>) {

        exchangeRateRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        val adapter = ExchangeAdapter(exchangeList)
        exchangeRateRecyclerView.adapter = adapter

        adapter.setOnClickListener(object : ExchangeAdapter.OnItemClickListener {
            override fun onClick(data: ExchangeData) {
                val intent = Intent(context, CurrencyCalculateActivity::class.java)
                intent.putExtra("data", data as Serializable)
                startActivity(intent)
            }
        })
    }

}
