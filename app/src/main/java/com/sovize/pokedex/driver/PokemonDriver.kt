package com.sovize.pokedex.driver

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.sovize.pokedex.utilities.ServerInfo
import com.sovize.pokedex.interfaces.PokemonInterface

class PokemonDriver {

    private var httpAdapter = Retrofit.Builder()
        .baseUrl(ServerInfo.baseURL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    /**
     * ScalarsConverterFactory its just code that will automatically parse
     * the http response into a Kotlin String
     * */

    fun getPokemonList(): PokemonInterface{
        return httpAdapter.create(PokemonInterface::class.java)
    }

}