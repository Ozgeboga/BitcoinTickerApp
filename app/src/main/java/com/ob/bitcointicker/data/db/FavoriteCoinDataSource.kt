package com.ob.bitcointicker.data.db

import javax.inject.Inject

class FavoriteCoinDataSource @Inject constructor(
    private val database: AppDatabase
)  {

    suspend fun insertIntoDB(coins : Coin){
        database.favDao().insert(coins)
    }

    suspend fun getFavoriteCoins() : List<Coin>{
        return database.favDao().getFavorites()
    }
}