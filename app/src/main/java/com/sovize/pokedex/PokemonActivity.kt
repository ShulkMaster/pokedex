package com.sovize.pokedex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class PokemonActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)
        /*
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
        */
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}