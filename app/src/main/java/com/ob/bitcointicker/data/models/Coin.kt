package com.ob.bitcointicker.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "id") val id : String?
)
