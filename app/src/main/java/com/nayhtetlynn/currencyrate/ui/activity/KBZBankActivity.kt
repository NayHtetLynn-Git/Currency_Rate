package com.nayhtetlynn.currencyrate.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nayhtetlynn.currencyrate.jsoup_parser.KBZExchangeRateParser
import com.nayhtetlynn.currencyrate.R
import com.nayhtetlynn.currencyrate.adapter.OtherExchangeAdapter
import com.nayhtetlynn.currencyrate.cache.Cache
import com.nayhtetlynn.currencyrate.model.ExchangeRate
import kotlinx.android.synthetic.main.activity_kbzbank.*

class KBZBankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kbzbank)
        supportActionBar?.hide()

        back.setOnClickListener {
            this.finish()
        }

        loadData()

    }

    private fun loadData() {
        val task = KBZExchangeRateParser(this)
        task.execute()

        if (Cache().checkListOtherBank(applicationContext,"kbz")) {
            Log.d("LogDataList", Cache().readExchangeListOtherBank(this@KBZBankActivity, "kbz").toString())
            Log.d("LogDataDate",Cache().getDate(this@KBZBankActivity,"kbzTimestamp"))
            updatedDate.text=Cache().getDate(this@KBZBankActivity,"kbzTimestamp")

            val list= Cache().readExchangeListOtherBank(this@KBZBankActivity, "kbz")

            if (list.size<=0){
                exchangeRateRecyclerView.visibility= View.GONE
                checkNetwork.visibility= View.VISIBLE
                checkNetwork.text=getString(R.string.no_data)
                return
            }
            exchangeRateRecyclerView.apply {
                layoutManager= LinearLayoutManager(this@KBZBankActivity)
            }
            val adapter= OtherExchangeAdapter(list)
            exchangeRateRecyclerView.adapter=adapter

            adapter.setOnClickListener(object : OtherExchangeAdapter.OnItemClickListener{
                override fun onClick(exchangeRate: ExchangeRate) {
//                            val intent = Intent(activity, CurrencyCalculateActivity::class.java)
//                            intent.putExtra("data", exchangeRate as Serializable)
//                            activity.startActivity(intent)
                    Toast.makeText(applicationContext,exchangeRate.currency, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
