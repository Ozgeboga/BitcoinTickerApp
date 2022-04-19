package com.ob.bitcointicker.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Coin(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "id") val id : String?,
    @ColumnInfo(name = "image") val image : String?,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "current_price") val current_price : Double?,
    @ColumnInfo(name = "price_change_percentage_24h") val price_change_percentage_24h : Double?,
    @ColumnInfo(name = "favorite") var favorite : Boolean
    ) : Serializable
