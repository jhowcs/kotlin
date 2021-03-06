package com.example.jonathancampos.marvelcharacterswithkotlin.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jonathancampos on 05/01/17.
 */
class BaseAPI {
    val mClient: OkHttpClient = OkHttpClient().newBuilder().addInterceptor(MarvelRequestInterceptor()).build()

    val mRetrofit = Retrofit.Builder()
    .baseUrl("http://gateway.marvel.com/v1/public/")
    .client(mClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

}
