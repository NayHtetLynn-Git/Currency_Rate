package com.nayhtetlynn.currencyrate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayhtetlynn.currencyrate.R
import com.nayhtetlynn.currencyrate.model.ExchangeData
import kotlinx.android.synthetic.main.exchange_rate_layout.view.*

class ExchangeAdapter(private val exchangeList: MutableList<ExchangeData>) : RecyclerView.Adapter<ExchangeAdapter.ViewHolder>(){

    private lateinit var clickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val exchangeView=LayoutInflater.from(parent.context)
            .inflate(R.layout.exchange_rate_layout,parent,false)
        return ViewHolder(exchangeView)
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exchangeData=exchangeList[position]

        holder.country.text=exchangeData.country
        holder.unit.text=exchangeData.unit
        holder.foreignRate.text=exchangeData.rate

        when(exchangeData.unit){
            "USD"->holder.flag.setImageResource(R.drawable.ic_united_states_flag)
            "EUR"->holder.flag.setImageResource(R.drawable.ic_eur_flag)
            "SGD"->holder.flag.setImageResource(R.drawable.ic_sgd_flag)
            "GBP"->holder.flag.setImageResource(R.drawable.ic_gbp_flag)
            "CHF"->holder.flag.setImageResource(R.drawable.ic_chf_flag)
            "JPY"->holder.flag.setImageResource(R.drawable.ic_jpy_flag)
            "AUD"->holder.flag.setImageResource(R.drawable.ic_aud_flag)
            "BDT"->holder.flag.setImageResource(R.drawable.ic_bdt_flag)
            "BND"->holder.flag.setImageResource(R.drawable.ic_bnd_flag)
            "KHR"->holder.flag.setImageResource(R.drawable.ic_khr_flag)
            "CAD"->holder.flag.setImageResource(R.drawable.ic_cad_flag)
            "CNY"->holder.flag.setImageResource(R.drawable.ic_cny_flag)
            "HKD"->holder.flag.setImageResource(R.drawable.ic_hkd_flag)
            "INR"->holder.flag.setImageResource(R.drawable.ic_inr_flag)
            "IDR"->holder.flag.setImageResource(R.drawable.ic_idr_flag)
            "KRW"->holder.flag.setImageResource(R.drawable.ic_krw_flag)
            "LAK"->holder.flag.setImageResource(R.drawable.ic_lak_flag)
            "MYR"->holder.flag.setImageResource(R.drawable.ic_myr_flag)
            "NZD"->holder.flag.setImageResource(R.drawable.ic_nzd_flag)
            "PKR"->holder.flag.setImageResource(R.drawable.ic_pkr_flag)
            "PHP"->holder.flag.setImageResource(R.drawable.ic_php_flag)
            "LKR"->holder.flag.setImageResource(R.drawable.ic_lkr_flag)
            "THB"->holder.flag.setImageResource(R.drawable.ic_thb_flag)
            "VND"->holder.flag.setImageResource(R.drawable.ic_vnd_flag)
            "BRL"->holder.flag.setImageResource(R.drawable.ic_brl_flag)
            "CZK"->holder.flag.setImageResource(R.drawable.ic_czk_flag)
            "DKK"->holder.flag.setImageResource(R.drawable.ic_dkk_flag)
            "EGP"->holder.flag.setImageResource(R.drawable.ic_egp_flag)
            "ILS"->holder.flag.setImageResource(R.drawable.ic_ils_flag)
            "KES"->holder.flag.setImageResource(R.drawable.ic_kes_flag)
            "KWD"->holder.flag.setImageResource(R.drawable.ic_kwd_flag)
            "NPR"->holder.flag.setImageResource(R.drawable.ic_npr_flag)
            "NOK"->holder.flag.setImageResource(R.drawable.ic_nok_flag)
            "RUB"->holder.flag.setImageResource(R.drawable.ic_rub_flag)
            "SAR"->holder.flag.setImageResource(R.drawable.ic_sar_flag)
            "RSD"->holder.flag.setImageResource(R.drawable.ic_rsd_flag)
            "ZAR"->holder.flag.setImageResource(R.drawable.ic_zar_flag)
            "SEK"->holder.flag.setImageResource(R.drawable.ic_sek_flag)
            else->holder.flag.setImageResource(R.drawable.ic_foreign_flag)
        }

        holder.itemView.setOnClickListener{
            val exchangeDataClick=exchangeList[position]
            clickListener.onClick(exchangeDataClick)
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val country: TextView =itemView.country
        val unit: TextView =itemView.unit
        val foreignRate:TextView=itemView.foreignRate
        val flag:ImageView=itemView.flag
    }

    interface OnItemClickListener {
        fun onClick(data: ExchangeData)
    }

    fun setOnClickListener(clickListener: OnItemClickListener){
        this.clickListener=clickListener
    }

}