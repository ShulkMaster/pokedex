package com.sovize.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sovize.pokedex.R
import com.sovize.pokedex.models.Pokemon

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
    }
}