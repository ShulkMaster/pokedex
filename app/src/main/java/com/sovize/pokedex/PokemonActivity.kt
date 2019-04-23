package com.sovize.pokedex

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sovize.pokedex.adapters.SpritesAdapter
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.AppKeys
import com.sovize.pokedex.utilities.Glider
import com.sovize.pokedex.utilities.ServerInfo

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
        setContent(intent?.getParcelableExtra(AppKeys.pokemon)!!)
    }

    private fun setContent(pokemon: Pokemon) {
        findViewById<TextView>(R.id.pokemon_name)?.text = pokemon.name
        findViewById<TextView>(R.id.pokemon_id)?.text = pokemon.id.toString()
        findViewById<TextView>(R.id.pokemon_exp)?.text = pokemon.base_experience.toString()
        findViewById<TextView>(R.id.pokemon_weight)?.text = pokemon.weight?.toString()
        findViewById<TextView>(R.id.pokemon_height)?.text = pokemon.height.toString()
        findViewById<RecyclerView>(R.id.rv_pokemon_sprites)?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = SpritesAdapter(pokemon.getSpriteList())
        }
        Glider().load(findViewById(R.id.scroller), "${ServerInfo.pokeSprite}${pokemon.id}.png", R.id.frontCover)
    }
}