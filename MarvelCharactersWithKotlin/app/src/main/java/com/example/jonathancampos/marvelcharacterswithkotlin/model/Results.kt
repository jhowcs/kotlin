package com.example.jonathancampos.marvelcharacterswithkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jonathancampos on 05/01/17.
 */
data class Results (@SerializedName("name") var name : String,
                    @SerializedName("description") var description : String,
                    @SerializedName("thumbnail") var thumbnail : Thumbnail){
}
