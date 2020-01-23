package com.nayhtetlynn.currencyrate.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nayhtetlynn.currencyrate.R
import com.nayhtetlynn.currencyrate.model.ExchangeData
import kotlinx.android.synthetic.main.activity_currency_calculate.*

class CurrencyCalculateActivity : AppCompatActivity() {

    private var currencyAmount = mutableListOf<String>()
    private var country = 0
    private var rate = "0.0"
    private var isFirst = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_calculate)
        supportActionBar?.hide()

        val exchangeData = intent.extras?.get("data") as ExchangeData

        foreign.text = exchangeData.country
        foreignAmount.text = getString(R.string.one)
        myanmarRate.text = exchangeData.rate
        rate = exchangeData.rate

        when (exchangeData.unit) {
            "USD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_united_states_flag, 0, 0, 0)
            "EUR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_eur_flag, 0, 0, 0)
            "SGD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sgd_flag, 0, 0, 0)
            "GBP" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gbp_flag, 0, 0, 0)
            "CHF" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chf_flag, 0, 0, 0)
            "JPY" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_jpy_flag, 0, 0, 0)
            "AUD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_aud_flag, 0, 0, 0)
            "BDT" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bdt_flag, 0, 0, 0)
            "BND" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bnd_flag, 0, 0, 0)
            "KHR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_khr_flag, 0, 0, 0)
            "CAD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cad_flag, 0, 0, 0)
            "CNY" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cny_flag, 0, 0, 0)
            "HKD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hkd_flag, 0, 0, 0)
            "INR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_inr_flag, 0, 0, 0)
            "IDR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_idr_flag, 0, 0, 0)
            "KRW" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_krw_flag, 0, 0, 0)
            "LAK" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lak_flag, 0, 0, 0)
            "MYR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_myr_flag, 0, 0, 0)
            "NZD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_nzd_flag, 0, 0, 0)
            "PKR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pkr_flag, 0, 0, 0)
            "PHP" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_php_flag, 0, 0, 0)
            "LKR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lkr_flag, 0, 0, 0)
            "THB" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thb_flag, 0, 0, 0)
            "VND" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_vnd_flag, 0, 0, 0)
            "BRL" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_brl_flag, 0, 0, 0)
            "CZK" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_czk_flag, 0, 0, 0)
            "DKK" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dkk_flag, 0, 0, 0)
            "EGP" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_egp_flag, 0, 0, 0)
            "ILS" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ils_flag, 0, 0, 0)
            "KES" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_kes_flag, 0, 0, 0)
            "KWD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_kwd_flag, 0, 0, 0)
            "NPR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_npr_flag, 0, 0, 0)
            "NOK" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_nok_flag, 0, 0, 0)
            "RUB" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rub_flag, 0, 0, 0)
            "SAR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sar_flag, 0, 0, 0)
            "RSD" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rsd_flag, 0, 0, 0)
            "ZAR" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_zar_flag, 0, 0, 0)
            "SEK" -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sek_flag, 0, 0, 0)
            else -> foreign.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_foreign_flag, 0, 0, 0)

        }

        btn0.setOnClickListener {
            calculateAdd(getString(R.string.zero), country, rate)
        }
        btn1.setOnClickListener {
            calculateAdd(getString(R.string.one), country, rate)
        }
        btn2.setOnClickListener {
            calculateAdd(getString(R.string.two), country, rate)
        }
        btn3.setOnClickListener {
            calculateAdd(getString(R.string.three), country, rate)
        }
        btn4.setOnClickListener {
            calculateAdd(getString(R.string.four), country, rate)
        }
        btn5.setOnClickListener {
            calculateAdd(getString(R.string.five), country, rate)
        }
        btn6.setOnClickListener {
            calculateAdd(getString(R.string.six), country, rate)
        }
        btn7.setOnClickListener {
            calculateAdd(getString(R.string.seven), country, rate)
        }
        btn8.setOnClickListener {
            calculateAdd(getString(R.string.eight), country, rate)
        }
        btn9.setOnClickListener {
            calculateAdd(getString(R.string.nine), country, rate)
        }
        btnPoint.setOnClickListener {
            calculateAdd(getString(R.string.point), country, rate)
        }
        btnDel.setOnClickListener {
            calculateRemove(country, rate)
        }
        myanmarRate.setOnClickListener {
            if (country == 0) {
                country = 1
                currencyAmount.clear()
                isFirst = true
            }
        }
        foreignAmount.setOnClickListener {
            if (country == 1) {
                country = 0
                currencyAmount.clear()
                isFirst = true
            }
        }

    }

    private fun calculateRemove(country: Int, rate: String) {

        if (country == 0) {
            if (isFirst) foreignAmount.text = "" else if (foreignAmount.text.isNotEmpty()) foreignAmount.text = foreignAmount.text.toString().substring(0, foreignAmount.text.length - 1)
            if (foreignAmount.text.isEmpty()) {
                myanmarRate.text = ""
                isFirst = true
                return
            }
            val calculateAmount = "%.2f".format(foreignAmount.text.toString().toDouble() * rate.replace(",", "").toDouble())
            myanmarRate.text = calculateAmount
        } else {
            if (isFirst) myanmarRate.text = "" else if (myanmarRate.text.isNotEmpty()) myanmarRate.text = myanmarRate.text.toString().substring(0, myanmarRate.text.length - 1)
            if (myanmarRate.text.isEmpty()) {
                foreignAmount.text = ""
                isFirst = true
                return
            }
            val calculateAmount = "%.3f".format( myanmarRate.text.toString().toDouble() / rate.replace(",", "").toDouble())
            foreignAmount.text = calculateAmount
        }
        isFirst = false
    }

    private fun calculateAdd(i: String, country: Int, rate: String) {

        if (country == 0) {

            if (foreignAmount.text.contains(".") && i==".") return

            if (isFirst) {
                if (i == ".") foreignAmount.text = "0." else foreignAmount.text = i
            }
            else {
                if(foreignAmount.length()==1 && foreignAmount.text.contains("0") && i!="."){
                    foreignAmount.text=i
                    return
                }
                foreignAmount.append(i)
            }

            val calculateAmount = "%.2f".format(foreignAmount.text.toString().toDouble() * rate.replace(",", "").toDouble())
            myanmarRate.text = calculateAmount
        }
        else {
            if (myanmarRate.text.contains(".") && i==".") return

            if (isFirst) {
                if (i == ".") myanmarRate.text = "0." else myanmarRate.text = i
            } else {
                if (myanmarRate.length() == 1 && myanmarRate.text.contains("0") && i != ".") {
                    myanmarRate.text = i
                    return
                }
                myanmarRate.append (i)
            }

            val calculateAmount = "%.3f".format(myanmarRate.text.toString().toDouble() / rate.replace(",", "").toDouble())
            foreignAmount.text = calculateAmount
        }
        isFirst = false
    }
}
