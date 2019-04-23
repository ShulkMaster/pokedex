package com.sovize.pokedex.models

import android.os.Parcel
import android.os.Parcelable

data class Sprites(
    var back_default: String?,
    var back_female: String?,
    var back_shiny: String?,
    var back_shiny_female: String?,
    var front_default: String?,
    var front_female: String?,
    var front_shiny: String?,
    var front_shiny_female: String?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
       dest?.writeString(back_default)
       dest?.writeString(back_female)
       dest?.writeString(back_shiny)
       dest?.writeString(back_shiny_female)
       dest?.writeString(front_default)
       dest?.writeString(front_female)
       dest?.writeString(front_shiny)
       dest?.writeString(front_shiny_female)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Sprites> {
        override fun createFromParcel(parcel: Parcel): Sprites {
            return Sprites(parcel)
        }

        override fun newArray(size: Int): Array<Sprites?> {
            return arrayOfNulls(size)
        }
    }
}