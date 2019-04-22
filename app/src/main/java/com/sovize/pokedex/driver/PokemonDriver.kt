package com.sovize.pokedex.driver

import retrofit2.Retrofit
import com.sovize.pokedex.utilities.ServerInfo
import com.sovize.pokedex.interfaces.PokemonInterface
import retrofit2.converter.scalars.ScalarsConverterFactory

class PokemonDriver {

    private var retrofit = Retrofit.Builder()
        .baseUrl(ServerInfo.baseURL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    /**
     * ScalarsConverterFactory its just code that will automatically parse
     * the http response into a Kotlin String
     * */

    fun getPokemonList(): PokemonInterface{
        return retrofit.create(PokemonInterface::class.java)
    }

}