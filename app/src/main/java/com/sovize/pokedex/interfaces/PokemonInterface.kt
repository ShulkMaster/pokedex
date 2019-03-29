package com.sovize.pokedex.interfaces

import retrofit2.Call
import retrofit2.http.GET
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.http.Query

interface PokemonInterface {

    @GET(ServerInfo.pokeURL)
    fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<String>
}