package com.ob.bitcointicker.data.app_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Database(entities = [Coin::class] , version = 3)
abstract class AppDatabase : RoomDatabase(){

    abstract fun coinDao() : CoinDao

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