package com.sovize.pokedex.interfaces

import com.sovize.pokedex.models.Pokelist
import com.sovize.pokedex.models.Pokemon
import com.sovize.pokedex.utilities.ServerInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonListInterface {

    @GET(ServerInfo.pokeURL)
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<Pokelist>

    @GET("${ServerInfo.pokeURL}{id}")
    fun getPokemonById(@Path("id") pokemonId: Int): Call<Pokemon>
}