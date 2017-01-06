package com.example.jonathancampos.marvelcharacterswithkotlin.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jonathancampos on 05/01/17.
 */
class BaseAPI {

    val mRetrofit = Retrofit.Builder()
    .baseUrl("http://gateway.marvel.com/v1/public/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

}
