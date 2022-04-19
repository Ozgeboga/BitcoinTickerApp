package com.ob.bitcointicker.core

import android.content.Context
import com.google.gson.GsonBuilder
import com.ob.bitcointicker.api.ApiService
import com.ob.bitcointicker.api.Interceptor
import com.ob.bitcointicker.data.db.AppDatabase
import com.ob.bitcointicker.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDB(context)

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(Interceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
         val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}