package com.example.jonathancampos.marvelcharacterswithkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.jonathancampos.marvelcharacterswithkotlin.R
import com.example.jonathancampos.marvelcharacterswithkotlin.model.Characters
import com.example.jonathancampos.marvelcharacterswithkotlin.model.Results
import java.util.*

/**
 * Created by jonathancampos on 05/01/17.
 */
class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var mCharacterList : List<Results>? = null

    fun setCharacterList(characterList : List<Results>?) {
        mCharacterList = characterList
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CharacterViewHolder {
        val view : View = LayoutInflater.from(parent?.context).inflate(R.layout.character_main_row, parent, false)

        val vh : CharacterViewHolder = CharacterViewHolder(view)

        return vh
    }

    override fun onBindViewHolder(holder: CharacterViewHolder?, position: Int) {
        holder?.imgCharacter?.setImageResource(R.mipmap.ic_launcher)

        val character : Results? = mCharacterList?.get(position)

        if(character != null) {

            val imageRes: String = character.thumbnail.path + "/" +
                    "portrait_xlarge." +
                    character.thumbnail.extension

            Glide.with(holder?.imgCharacter?.context)
                    .load(imageRes)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder?.imgCharacter)

            holder?.txtCharacterName?.text = character.name
        }

        //holder?.imgCharacter?.setImageResource(R.mipmap.ic_launcher)
    }

    override fun getItemCount(): Int {
        return mCharacterList?.size ?: 0
    }

    class CharacterViewHolder(itemView: View?) : ViewHolder(itemView) {
        val imgCharacter : ImageView by lazy { itemView?.findViewById(R.id.imgCharacter) as ImageView }
        val txtCharacterName : TextView by lazy { itemView?.findViewById(R.id.txtCharacterName) as TextView }
    }
}