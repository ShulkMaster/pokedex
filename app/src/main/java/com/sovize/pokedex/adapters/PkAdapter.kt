package com.sovize.pokedex.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.sovize.pokedex.R
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.ServerInfo

class PkAdapter(
    private val pokemon_list: List<Pokemon>,
    private val clickFunction: (Pokemon) -> Unit)
    : RecyclerView.Adapter<PkAdapter.PkViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PkViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.pokemon_template, p0, false)
        return PkViewHolder(v)
    }


    override fun getItemCount(): Int = pokemon_list.size

    override fun onBindViewHolder(p0: PkViewHolder, p1: Int) {
        p0.bind(pokemon_list[p1], clickFunction)
    }

    class PkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Pokemon, clickFunction: (Pokemon) -> Unit) = with(itemView) {
            itemView.findViewById<TextView>(R.id.pk_id).text = item.id.toString()
            itemView.findViewById<TextView>(R.id.pk_name).text = item.name
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(itemView.context).load("${ServerInfo.pokeSprite}${item.id}.png")
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .placeholder(R.drawable.loading)
                .error(R.drawable.ic_broken_image_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.findViewById(R.id.pk_cover))
            this.setOnClickListener{clickFunction(item)}
        }
    }
}