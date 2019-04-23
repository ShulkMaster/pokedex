package com.sovize.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.sovize.pokedex.R
import com.sovize.pokedex.adapters.SpritesAdapter
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.ServerInfo

class PokemonFragment: Fragment() {

    private val etiquette = "PokemonFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.pokemon_details, container, false)
    }

    fun setPokemonView(pokemon: Pokemon){
        view?.findViewById<TextView>(R.id.pokemon_name)?.text = pokemon.name
        view?.findViewById<TextView>(R.id.pokemon_id)?.text = pokemon.id.toString()
        view?.findViewById<TextView>(R.id.pokemon_exp)?.text = pokemon.base_experience.toString()
        view?.findViewById<TextView>(R.id.pokemon_weight)?.text = pokemon.weight.toString()
        view?.findViewById<TextView>(R.id.pokemon_height)?.text = pokemon.height.toString()
        view?.findViewById<RecyclerView>(R.id.rv_pokemon_sprites)?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = SpritesAdapter(pokemon.getSpriteList())
        }
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(view?.context!!)
            .load("${ServerInfo.pokeSprite}${pokemon.id}.png")
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .placeholder(R.drawable.loading)
            .error(R.drawable.ic_broken_image_black_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view?.findViewById(R.id.frontCover)!!)
    }
}