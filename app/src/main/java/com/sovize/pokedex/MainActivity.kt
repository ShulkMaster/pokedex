package com.sovize.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.driver.PokemonDriver
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val pokeDriver = PokemonDriver()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemon: MutableList<Pokemon> = MutableList(40) {
            Pokemon(0, resources.getString(R.string.loading), resources.getString(R.string.loading))
        }

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PkAdapter(pokemon)
        }

        pokeDriver.getPokemonList()
            .getPokemons(100).enqueue(
                object : Callback<String> {

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        when (response.code()) {
                            200 -> {
                                Log.d(TAG, "Repose: " + response.body())
                                val pokemonLogs = JSONObject(response.body()).getJSONArray("results")
                                for(i in 0 until 40){
                                    pokemon[i].apply {
                                        id = i
                                        name = pokemonLogs.getJSONObject(i).optString("name")
                                        url = pokemonLogs.getJSONObject(i).optString("url")
                                    }
                                }
                                rv_pokemon_list.adapter?.notifyDataSetChanged()
                            }

                            else -> {
                                val httpCode = response.code().toString()
                                val httpsMessage = response.message().toString()
                                Log.e(TAG, "$httpCode: $httpsMessage")
                            }
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                }
            )
    }


}
