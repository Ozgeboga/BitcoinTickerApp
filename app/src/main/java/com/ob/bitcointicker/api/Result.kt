package com.ob.bitcointicker.api

import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

sealed class Result <out T> {
    data class Success<out  T>(val data : T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()

    companion object {
        inline fun <T> of(func : () -> Response<T> ) : Result<T> {
            return try {
                val response = func()
                if (response.code() in 200..299) {
                    val data = response.body()!!
                    Success(data)
                } else {
                    if (response.code() in 400..499) {
                        throw HttpException(response)
                    } else throw IOException(response.message())
                }
            } catch (throwable: Throwable) {
                Error(throwable)
            }
        }
    }
}
