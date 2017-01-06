package com.example.jonathancampos.marvelcharacterswithkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jonathancampos on 05/01/17.
 */
data class Characters (@SerializedName("copyright") var copyright : String,
                       @SerializedName("attributionText") var attributionText : String,
                       @SerializedName("etag") var etag : String,
                       @SerializedName("data") var data : Data){
}