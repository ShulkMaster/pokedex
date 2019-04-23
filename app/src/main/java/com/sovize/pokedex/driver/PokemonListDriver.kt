package com.sovize.pokedex.driver

import android.util.Log
import com.sovize.pokedex.interfaces.PokemonListInterface
import com.sovize.pokedex.models.Pokelist
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListDriver(pokemonArray: ArrayList<Pokemon>) {

    private val tag = "PokemonListDriver"
    private val pokemonData= pokemonArray

    private var retrofit = Retrofit.Builder()
        .baseUrl(ServerInfo.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokemonList(afterMethod: () -> Unit) {
        retrofit.create(PokemonListInterface::class.java)
            .getPokemonList(pokemonData.size, 50).enqueue(object : Callback<Pokelist> {
                override fun onFailure(call: Call<Pokelist>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Pokelist>, response: Response<Pokelist>) {
                    when (response.code()) {
                        200 -> {
                            val pokemonCounter = pokemonData.size
                            for (x: Int in 0 until response.body()?.results?.size!!) {
                                response.body()?.results?.get(x)?.id = x + pokemonCounter + 1
                                Log.d(
                                    tag,
                                    "${response.body()?.results?.get(x)?.name} with ID: ${response.body()?.results?.get(
                                        x
                                    )?.id}"
                                )
                                pokemonData.add(response.body()?.results?.get(x)!!)
                            }
                            afterMethod()
                        }

                        else -> {
                            val httpCode = response.code().toString()
                            val httpsMessage = response.message().toString()
                            Log.e(tag, "$httpCode: $httpsMessage")
                        }
                    }
                }

            })
    }

    fun getPokemonById(id: Int, afterMethod: (Pokemon) -> Unit) {
        if (pokemonData[id-1].weight == null){
            retrofit.create(PokemonListInterface::class.java)
                .getPokemonById(id).enqueue(object : Callback<Pokemon> {

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                        when (response.code()) {
                            200 -> {
                                Log.d(tag, response.body()!!.location_area_encounters)
                                Log.d(tag, "El pokemon details no estaba")
                                response.body()!!.url = pokemonData[id-1].url
                                pokemonData[id-1] = response.body()!!
                                afterMethod(response.body()!!)
                            }

                            else -> {
                                val httpCode = response.code().toString()
                                val httpsMessage = response.message().toString()
                                Log.e(tag, "$httpCode: $httpsMessage")
                            }
                        }
                    }
                })
        } else {
            afterMethod(pokemonData[id-1])
        }
    }
}