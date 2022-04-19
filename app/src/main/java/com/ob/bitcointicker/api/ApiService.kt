package com.ob.bitcointicker.api

import com.ob.bitcointicker.data.model.CoinDetailResponse
import com.ob.bitcointicker.data.model.CoinListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("coins")
    suspend fun fetchCoinList() : Response<ArrayList<CoinListResponse>>

    @GET("coins/{id}")
    suspend fun coinDetail(@Path( "id")  id : String ) : Response<CoinDetailResponse>

}