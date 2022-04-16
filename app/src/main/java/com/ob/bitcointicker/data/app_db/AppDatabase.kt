package com.ob.bitcointicker.data.app_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Database(entities = [Coin::class] , version = 1)
abstract class AppDatabase : RoomDatabase(){
    @Provides
    abstract fun coinDao() : CoinDao

    companion object {
        fun buildDB(context: Context){
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }
}