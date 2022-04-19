package com.ob.bitcointicker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coin::class] , version = 4)
abstract class AppDatabase : RoomDatabase(){

    abstract fun coinDao() : CoinDao
    abstract fun favDao() : FavoriteDao

    companion object {
        fun buildDB(context: Context) : AppDatabase{
         return   Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name")
             .fallbackToDestructiveMigration()
             .build()
        }
    }
}