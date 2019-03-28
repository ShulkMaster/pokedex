package com.sovize.pokedex.interfaces

import retrofit2.Call
import retrofit2.http.GET
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.http.Query

interface pokemonInterface {

    @GET(ServerInfo.pokeURL)
    fun getPokemons(@Query("limit") index: Int): Call<String>
}