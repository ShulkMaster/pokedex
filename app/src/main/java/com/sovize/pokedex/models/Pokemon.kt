package com.sovize.pokedex.models

data class Pokemon(
    var id: Int,
    var name: String,
    var url: String,
    var base_experience: Int,
    var weight: Int?,
    var height: Int,
    var is_default: Boolean,
    var location_area_encounters: String
)