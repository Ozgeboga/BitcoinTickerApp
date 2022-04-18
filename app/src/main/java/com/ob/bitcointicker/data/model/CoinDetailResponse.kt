package com.ob.bitcointicker.data.model

import java.io.Serializable

data class CoinDetailResponse(
    val id: String?,
    val image: Image?,
    val market_cap_rank: Int,
    val market_data: MarketData,
    val name: String,
    val symbol: String,
    val description: Description
) : Serializable