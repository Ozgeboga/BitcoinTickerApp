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
   suspend fun makeCoinListRequest() : Response<ArrayList<CoinListResponse>> {
        return service.fetchCoinList()
    }

    suspend fun makeCoinDetailRequest(id : String) : Response<CoinDetailResponse> {
        return service.coinDetail(id)
    }
}