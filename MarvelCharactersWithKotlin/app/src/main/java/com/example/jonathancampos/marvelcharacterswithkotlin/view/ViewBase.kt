package com.example.jonathancampos.marvelcharacterswithkotlin.view

import com.example.jonathancampos.marvelcharacterswithkotlin.model.Characters

/**
 * Created by jonathancampos on 05/01/17.
 */
public interface ViewBase {

    fun onRenderList(list : Characters)

}