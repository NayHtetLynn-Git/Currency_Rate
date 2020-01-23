package com.nayhtetlynn.currencyrate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayhtetlynn.currencyrate.R
import com.nayhtetlynn.currencyrate.model.ExchangeRate
import kotlinx.android.synthetic.main.other_exchange_rate_layout.view.*

class OtherExchangeAdapter(private val exchangeList: MutableList<ExchangeRate>) : RecyclerView.Adapter<OtherExchangeAdapter.ViewHolder>() {

    private lateinit var clickListener:OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val exchangeView= LayoutInflater.from(parent.context)
            .inflate(R.layout.other_exchange_rate_layout,parent,false)
        return ViewHolder(exchangeView)
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exchangeRate=exchangeList[position]

        holder.currency.text=exchangeRate.currency
        holder.buyRate.text=exchangeRate.buyRate
        holder.sellRate.text=exchangeRate.sellRate

        holder.itemView.setOnClickListener {
            val exchangeRateData=exchangeList[position]
            clickListener.onClick(exchangeRateData)
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val currency:TextView=itemView.currency
        val buyRate:TextView=itemView.buyRate
        val sellRate:TextView=itemView.sellRate
    }

    interface OnItemClickListener{
        fun onClick(exchangeRate: ExchangeRate)
    }

    fun setOnClickListener(clickListener: OnItemClickListener){
        this.clickListener=clickListener
    }
}