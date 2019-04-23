package com.sovize.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.sovize.pokedex.R
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory




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
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                Glide.with(itemView.context)
                    .load(item)
                    .transition(withCrossFade(factory))
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.findViewById(R.id.poke_sprite))
            }
        }
    }
}