package ru.potemkin.appcrypto2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_coin_price_list.*
import ru.potemkin.appcrypto2.adapters.CoinInfoAdapter
import ru.potemkin.appcrypto2.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }

        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })

    }



}