package com.ob.bitcointicker.data.app_db

import javax.inject.Inject

class CoinListDataSource @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertIntoDB(coins : List<Coin>){
        if(coins.isNotEmpty()) database.coinDao().insert(coins)
    }
}