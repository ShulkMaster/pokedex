package com.sovize.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sovize.pokedex.R
import com.sovize.pokedex.utilities.Glider


class SpritesAdapter(private val spriteList: List<String>) : RecyclerView.Adapter<SpritesAdapter.SpriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpriteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_sprites, parent, false)
        return SpriteViewHolder(v)
    }

    override fun getItemCount(): Int = spriteList.size

    override fun onBindViewHolder(holder: SpriteViewHolder, position: Int) {
        holder.bind(spriteList[position])
    }


    class SpriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            if (item != "") {
                Glider().load(itemView, item, R.id.poke_sprite)
            }else {
                val icon = itemView.resources.getDrawable(R.drawable.missing)
                itemView.findViewById<ImageView>(R.id.poke_sprite).setImageDrawable(icon)
            }
        }
    }
}