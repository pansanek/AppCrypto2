package ru.potemkin.appcrypto2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*
import ru.potemkin.appcrypto2.R
import ru.potemkin.appcrypto2.pojo.CoinPriceInfo

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList:List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.ivLogoCoin
        val tvPrice = itemView.tvPrice
        val tvLastUpdate = itemView.tvLastUpdate
        val tvSymbols = itemView.tvSymbols
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount() = coinInfoList.size


    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        holder.tvSymbols.text = String.format(symbolsTemplate,coin.fromSymbol,coin.toSymbol)
        holder.tvPrice.text = coin.price
        holder.tvLastUpdate.text = String.format(lastUpdateTemplate,coin.getFormattedTime())
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}