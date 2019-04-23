package com.sovize.pokedex.utilities

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.sovize.pokedex.R

class Glider {

    private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    fun load(v: View?, address: String, id: Int){
        if (v != null) {
            Glide.with(v.context).load(address)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .placeholder(R.drawable.loading)
                .error(R.drawable.ic_broken_image_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v.findViewById(id))
        }
    }

}