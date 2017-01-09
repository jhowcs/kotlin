package com.example.jonathancampos.marvelcharacterswithkotlin.network.service

import com.example.jonathancampos.marvelcharacterswithkotlin.model.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jonathancampos on 05/01/17.
 */
public interface CharacterService {

    @GET("characters")
    fun getCharacterList() : Call<Characters>
}