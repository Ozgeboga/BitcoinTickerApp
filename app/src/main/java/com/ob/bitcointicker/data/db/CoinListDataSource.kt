package com.ob.bitcointicker.data.db

import javax.inject.Inject

class CoinListDataSource @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertIntoDB(coins : List<Coin>){
        if(coins.isNotEmpty()) database.coinDao().insert(coins)
    }

    suspend fun findCoinsUsingParams(param : String) : List<Coin> {
        return database.coinDao().findByName(param)
    }
}