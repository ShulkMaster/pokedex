package com.sovize.pokedex.driver

import android.util.Log
import com.sovize.pokedex.interfaces.PokelistInterface
import com.sovize.pokedex.models.Pokelist
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListDriver {

    private val TAG = "PokemonListDriver"

    private var retrofit = Retrofit.Builder()
        .baseUrl(ServerInfo.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokemonList(dataFill: ArrayList<Pokemon>, afterMethod: () -> Unit) {
        retrofit.create(PokelistInterface::class.java)
            .getPokemonList(dataFill.size, 50).enqueue(object : Callback<Pokelist> {
                override fun onFailure(call: Call<Pokelist>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Pokelist>, response: Response<Pokelist>) {
                    when (response.code()) {
                        200 -> {
                            val pokemonCounter = dataFill.size
                            for ( x: Int in 0 until 50){
                                response.body()?.results?.get(x)?.id = x + pokemonCounter + 1
                                Log.d(TAG, "${response.body()?.results?.get(x)?.name} with ID: ${response.body()?.results?.get(x)?.id}")
                                dataFill.add(response.body()?.results?.get(x)!!)
                            }
                            afterMethod()
                        }

                        else -> {
                            val httpCode = response.code().toString()
                            val httpsMessage = response.message().toString()
                            Log.e(TAG, "$httpCode: $httpsMessage")
                        }
                    }
                }

            })
    }
}