package com.ob.bitcointicker.dao

import androidx.room.Dao
import androidx.room.Query
import com.ob.bitcointicker.data.models.Coin

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin WHERE id  LIKE :param")
    fun findByName(param : String) : Coin
}