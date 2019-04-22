package com.sovize.pokedex.interfaces

import com.sovize.pokedex.models.Pokelist
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokelistInterface {

    @GET(ServerInfo.pokeURL)
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<Pokelist>
}