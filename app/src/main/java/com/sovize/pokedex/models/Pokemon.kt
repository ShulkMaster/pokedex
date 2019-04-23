package com.sovize.pokedex.models

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(
    var id: Int,
    var name: String,
    var url: String,
    var base_experience: Int,
    var weight: Int?,
    var height: Int,
    var is_default: Boolean,
    var location_area_encounters: String?,
    var sprites: Sprites?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readValue(String::class.java.classLoader) as? String,
        parcel.readParcelable(Sprites::class.java.classLoader) as? Sprites
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeInt(base_experience)
        parcel.writeValue(weight)
        parcel.writeInt(height)
        parcel.writeByte(if (is_default) 1 else 0)
        parcel.writeString(location_area_encounters)
        parcel.writeParcelable(sprites, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }

    fun getSpriteList(): ArrayList<String>{
        val dataPack = ArrayList<String>()
        dataPack.add(sprites?.back_default?:"")
        dataPack.add(sprites?.back_female?:"")
        dataPack.add(sprites?.back_shiny?:"")
        dataPack.add(sprites?.back_shiny_female?:"")
        dataPack.add(sprites?.front_default?:"")
        dataPack.add(sprites?.front_female?:"")
        dataPack.add(sprites?.front_shiny?:"")
        dataPack.add(sprites?.front_shiny_female?:"")
        return dataPack
    }
}

