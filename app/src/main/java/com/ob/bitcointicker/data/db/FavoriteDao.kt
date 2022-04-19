package com.ob.bitcointicker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite : Coin)

    @Query("SELECT * FROM coin WHERE favorite = 1")
    suspend fun getFavorites(): List<Coin>
}