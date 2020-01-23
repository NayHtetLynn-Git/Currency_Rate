package com.nayhtetlynn.currencyrate.jsoup_parser

import android.os.AsyncTask
import android.util.Log
import com.nayhtetlynn.currencyrate.cache.Cache
import com.nayhtetlynn.currencyrate.model.ExchangeRate
import com.nayhtetlynn.currencyrate.ui.activity.KBZBankActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception
import java.lang.ref.WeakReference

class KBZExchangeRateParser internal constructor(context: KBZBankActivity) : AsyncTask<Void, Void, MutableList<ExchangeRate>>(){
    private var kbzExchangeList = mutableListOf<ExchangeRate>()
    private var updatedDate:String?=null

    private val activityReference: WeakReference<KBZBankActivity> = WeakReference(context)

    override fun doInBackground(vararg p0: Void?): MutableList<ExchangeRate> {
        try {
            val document: Document = Jsoup.connect("https://www.kbzbank.com/en/").get()

            updatedDate=document
                .select("div > div.wp-block-kadence-column.inner-column-1 > div > p.has-text-color.has-vivid-red-color")
                .first()
                .text()

            val usdBuyRate = document
                .select("div > div.wp-block-kadence-column.inner-column-1 > div > p:nth-child(2)")[1]
                .text()
            val usdSellRate = document
                .select("div > div.wp-block-kadence-column.inner-column-1 > div > p:nth-child(3)")
                .first()
                .text()
            val exchangeUSD=ExchangeRate("USD",usdBuyRate.substringAfter("Buy –").trim(),usdSellRate.substringAfter("Sell –").trim())
            kbzExchangeList.add(exchangeUSD)

            val sgdBuyRate = document
                .select("div > div.wp-block-kadence-column.inner-column-2 > div > p:nth-child(2)")
                .first()
                .text()
            val sgdSellRate = document
                .select("div > div.wp-block-kadence-column.inner-column-2 > div > p:nth-child(3)")
                .first()
                .text()
            val exchangeSGD=ExchangeRate("SGD",sgdBuyRate.substringAfter("Buy –").trim(),sgdSellRate.substringAfter("Sell –").trim())
            kbzExchangeList.add(exchangeSGD)

            val eurBuyRate = document
                .select("div > div.wp-block-kadence-column.inner-column-3 > div > p:nth-child(2)")
                .text()
            val eurSellRate = document
                .select("div > div.wp-block-kadence-column.inner-column-3 > div > p:nth-child(3)")
                .text()
            val exchangeEUR=ExchangeRate("EUR",eurBuyRate.substringAfter("Buy –").trim(),eurSellRate.substringAfter("Sell –").trim())
            kbzExchangeList.add(exchangeEUR)

            val thbBuyRate = document
                .select("div > div.wp-block-kadence-column.inner-column-4 > div > p:nth-child(2)")
                .text()
            val thbSellRate = document
                .select("div > div.wp-block-kadence-column.inner-column-4 > div > p:nth-child(3)")
                .text()
            val exchangeTHB=ExchangeRate("THB",thbBuyRate.substringAfter("Buy –").trim(),thbSellRate.substringAfter("Sell –").trim())
            kbzExchangeList.add(exchangeTHB)
        }catch (e: Exception){
            Log.d("LogData","No Result")
        }
        return kbzExchangeList
    }

    override fun onPostExecute(result: MutableList<ExchangeRate>?) {
        super.onPostExecute(result)
        val activity=activityReference.get()
        if (activity==null || activity.isFinishing)return
        if (result?.size!=0){
            Cache().saveData(activity,result!!,"kbz")
            Cache().saveDate(activity,updatedDate?.substringAfter("Date –")?.trim().toString(),"kbzTimestamp")
        }
    }

}