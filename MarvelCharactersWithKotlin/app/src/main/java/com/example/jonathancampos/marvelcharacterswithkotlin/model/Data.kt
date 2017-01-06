package com.example.jonathancampos.marvelcharacterswithkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jonathancampos on 05/01/17.
 */
data class Data (@SerializedName("results") var results : List<Results>) {
}

