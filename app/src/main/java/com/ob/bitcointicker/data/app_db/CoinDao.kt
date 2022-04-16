package com.ob.bitcointicker.data.app_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin WHERE id  LIKE :param")
    fun findByName(param : String) : Coin

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: List<Coin>)

    @Query("SELECT * FROM coin")
    suspend fun getAll(): List<Coin>
}