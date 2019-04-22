package com.sovize.pokedex.models

data class Pokelist(
    var count: Int,
    var next: String,
    var previous: String,
    var results: ArrayList<Pokemon>
)