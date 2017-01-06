package com.example.jonathancampos.marvelcharacterswithkotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jonathancampos.marvelcharacterswithkotlin.R
import com.example.jonathancampos.marvelcharacterswithkotlin.adapter.CharacterAdapter
import com.example.jonathancampos.marvelcharacterswithkotlin.model.Characters
import com.example.jonathancampos.marvelcharacterswithkotlin.presenter.CharacterPresenter

class MainActivity : AppCompatActivity(), ViewBase {

    val mRV : RecyclerView by lazy {findViewById(R.id.rv_characters) as RecyclerView}
    val mAdapter : CharacterAdapter = CharacterAdapter()
    val mPresenter : CharacterPresenter = CharacterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        if(savedInstanceState == null) {
            mPresenter.loadData()
        }

    }

    fun initRecyclerView() {
        val glm : GridLayoutManager = GridLayoutManager(this, 2)

        mRV.adapter = mAdapter

        mRV.layoutManager = glm
    }

    override fun onRenderList(result: Characters) {
        if(result != null && result.data.results != null) {
            mAdapter.setCharacterList(result.data.results)
            mAdapter.notifyDataSetChanged()
        }
    }
}