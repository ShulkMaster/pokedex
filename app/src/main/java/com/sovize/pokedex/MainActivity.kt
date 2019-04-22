package com.sovize.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.driver.PokemonListDriver

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private var pokemon = ArrayList<Pokemon>()
    private var loadBlocker = false
    private val pokemonLoader =  PokemonListDriver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PkAdapter(pokemon)
        }

        rv_pokemon_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !loadBlocker) {
                    loadBlocker = true
                    Log.d(tag, "Load blocker on")
                    pokemonLoader.getPokemonList(pokemon){
                        rv_pokemon_list.adapter?.notifyDataSetChanged()
                        loadBlocker = false
                    }
                }
            }
        })

        pokemonLoader.getPokemonList(pokemon) {
            rv_pokemon_list.adapter?.notifyDataSetChanged()
        }

    }


}
