package com.ob.bitcointicker.data.model

data class MarketData(
    val current_price: CurrentPrice,
    val high_24h: High24h,
    val last_updated: String,
    val low_24h: Low24h,
    val market_cap: MarketCap,
    val market_cap_change_percentage_24h: Double,
    val market_cap_change_percentage_24h_in_currency: MarketCapChangePercentage24hInCurrency,
    val market_cap_rank: Int,
    val max_supply: Double,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
)