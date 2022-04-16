package com.ob.bitcointicker.data.app_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "id") val id : String?
)
