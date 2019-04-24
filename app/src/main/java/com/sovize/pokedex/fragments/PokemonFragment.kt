package com.sovize.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sovize.pokedex.R
import com.sovize.pokedex.adapters.SpritesAdapter
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.Glider
import com.sovize.pokedex.utilities.ServerInfo

class PokemonFragment: Fragment() {

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
        Glider().load(view, "${ServerInfo.pokeSprite}${pokemon.id}.png", R.id.frontCover)
    }
}