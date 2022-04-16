package com.ob.bitcointicker.api

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

}