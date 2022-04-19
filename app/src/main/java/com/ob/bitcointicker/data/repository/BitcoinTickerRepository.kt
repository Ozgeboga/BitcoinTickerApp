package com.ob.bitcointicker.data.repository

import com.ob.bitcointicker.api.ApiService
import com.ob.bitcointicker.data.model.CoinDetailResponse
import com.ob.bitcointicker.data.model.CoinListResponse
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.ArrayList

class BitcoinTickerRepository @Inject constructor(
        private val service : ApiService
) {
   suspend fun makeCoinListRequest(currency: String) : Response<ArrayList<CoinListResponse>> {
        return service.fetchCoinList( currency)
    }

    suspend fun makeCoinDetailRequest(id : String) : Response<CoinDetailResponse> {
        return service.coinDetail(id)
    }
}