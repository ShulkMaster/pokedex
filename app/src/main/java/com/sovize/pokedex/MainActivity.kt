package com.sovize.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.FrameLayout
import com.sovize.pokedex.adapters.PkAdapter
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.driver.PokemonListDriver
import com.sovize.pokedex.fragments.PokemonFragment

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private var pokemon = ArrayList<Pokemon>()
    private var loadBlocker = false
    private val pokemonLoader = PokemonListDriver(pokemon)
    private val pokemonDetail = PokemonFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(findViewById<FrameLayout>(R.id.content) != null){
            supportFragmentManager.beginTransaction().replace(R.id.content, pokemonDetail).commit()
        }

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PkAdapter(pokemon) {
                pokemonLoader.getPokemonById(it.id, pokemonDetail::setPokemonView)
            }
        }

        rv_pokemon_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !loadBlocker) {
                    loadBlocker = true
                    Log.d(tag, "Load blocker on")
                    pokemonLoader.getPokemonList{
                        rv_pokemon_list.adapter?.notifyDataSetChanged()
                        loadBlocker = false
                    }
                }
            }
        })

        pokemonLoader.getPokemonList{
            rv_pokemon_list.adapter?.notifyDataSetChanged()
        }

    }


}
