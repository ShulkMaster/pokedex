package com.sovize.pokedex.models

data class Pokemon(
    var id: Int,
    var name: String,
    var url: String,
    var base_experience: Int,
    var weight: Int?,
    var height: Int,
    var is_default: Boolean,
    var location_area_encounters: String,
    var sprites: Sprites
){
    fun getSpriteList(): ArrayList<String>{
        val dataPack = ArrayList<String>()
        dataPack.add(sprites.back_default?:"")
        dataPack.add(sprites.back_female?:"")
        dataPack.add(sprites.back_shiny?:"")
        dataPack.add(sprites.back_shiny_female?:"")
        dataPack.add(sprites.front_default?:"")
        dataPack.add(sprites.front_female?:"")
        dataPack.add(sprites.front_shiny?:"")
        dataPack.add(sprites.front_shiny_female?:"")
        return dataPack
    }
}