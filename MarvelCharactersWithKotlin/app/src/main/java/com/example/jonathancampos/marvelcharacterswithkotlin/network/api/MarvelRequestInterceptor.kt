package com.example.jonathancampos.marvelcharacterswithkotlin.network.api

import android.os.SystemClock
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by jonathancampos on 09/01/17.
 */
class MarvelRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()

        val timestamp : Long = 1483984239 //SystemClock.currentThreadTimeMillis()

        val url = request.url().newBuilder()
                .addQueryParameter("ts", timestamp.toString())
                .addQueryParameter("apikey", "your_public_api_key")
                .addQueryParameter("hash", "md5(ts+private_key+public_key)")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(newRequest)
    }
}