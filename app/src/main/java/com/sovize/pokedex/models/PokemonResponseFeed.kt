package com.sovize.pokedex.models

data class PokemonResponseFeed (
    val count: Int,
    val next: String,
    val results: List<Pokemon>
)