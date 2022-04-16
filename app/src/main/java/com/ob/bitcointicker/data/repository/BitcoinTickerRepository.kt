package com.ob.bitcointicker.data.repository

import com.ob.bitcointicker.api.RetrofitHelper
import com.ob.bitcointicker.data.model.CoinListResponse
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.ArrayList

class BitcoinTickerRepository @Inject constructor(

) {
   suspend fun makeCoinListRequest(currency: String) : Response<ArrayList<CoinListResponse>> {
        return RetrofitHelper.service.fetchCoinList( currency)
    }
}