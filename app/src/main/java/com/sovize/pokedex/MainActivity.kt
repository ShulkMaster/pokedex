package com.sovize.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.driver.PokemonDriver
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val pokeDriver = PokemonDriver()
    private val tag = "MainActivity"
    private var pokemon = ArrayList<Pokemon>()
    private var loadBloker = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stackPokemonIndex()

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PkAdapter(pokemon)
        }

        rv_pokemon_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !loadBloker) {
                    loadBloker = true
                    Log.d(tag, "Load blocker on")
                    stackPokemonIndex()
                }
            }
        })
    }

    private fun stackPokemonIndex() {
        pokeDriver.getPokemonList()
            .getPokemon(pokemon.size, 50).enqueue(
                object : Callback<String> {

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        when (response.code()) {
                            200 -> {
                                Log.d(tag, "Repose: " + response.body())
                                val pokemonLogs = JSONObject(response.body()).getJSONArray("results")
                                for (i in 0 until 50) {
                                    pokemon.add(
                                        Pokemon(
                                            pokemon.size + 1 ,
                                            pokemonLogs.getJSONObject(i).optString("name"),
                                            pokemonLogs.getJSONObject(i).optString("url")
                                        )
                                    )
                                }
                                rv_pokemon_list.adapter?.notifyDataSetChanged()
                            }

                            else -> {
                                val httpCode = response.code().toString()
                                val httpsMessage = response.message().toString()
                                Log.e(tag, "$httpCode: $httpsMessage")
                            }
                        }
                        loadBloker = false
                        Log.d(tag, "Load blocker off")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                }
            )
    }

}
