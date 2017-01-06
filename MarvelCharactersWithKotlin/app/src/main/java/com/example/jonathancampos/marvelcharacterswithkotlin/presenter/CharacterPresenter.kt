package com.example.jonathancampos.marvelcharacterswithkotlin.presenter

import android.os.SystemClock
import android.util.Log
import com.example.jonathancampos.marvelcharacterswithkotlin.model.Characters
import com.example.jonathancampos.marvelcharacterswithkotlin.network.api.BaseAPI
import com.example.jonathancampos.marvelcharacterswithkotlin.network.service.CharacterService
import com.example.jonathancampos.marvelcharacterswithkotlin.view.ViewBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by jonathancampos on 05/01/17.
 */
class CharacterPresenter(view : ViewBase) {
    val TAG : String = "CharacterPresenter"

    val mView : ViewBase = view

    fun loadData() {
        val retrofit : Retrofit = BaseAPI().mRetrofit

        val service : CharacterService = retrofit.create(CharacterService::class.java)

        val timestamp : Long = SystemClock.currentThreadTimeMillis()

        val call : Call<Characters> = service.getCharacterList(timestamp,
                "apikey", "hash")

        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>?, response: Response<Characters>?) {
                if(response != null && response.isSuccessful) {
                    Log.i(TAG, "onResponse")

                    mView.onRenderList(response.body())
                }
            }

            override fun onFailure(call: Call<Characters>?, t: Throwable?) {
                Log.i(TAG, "onFailure")
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}