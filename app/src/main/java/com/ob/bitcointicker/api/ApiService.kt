package com.ob.bitcointicker.api

import com.ob.bitcointicker.data.model.CoinListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("coins/markets")
    suspend fun fetchCoinList(@Query("vs_currency") currency: String) : Response<ArrayList<CoinListResponse>>
}