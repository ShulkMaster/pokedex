package com.sovize.pokedex

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sovize.pokedex.models.Pokemon
import kotlinx.android.synthetic.main.pokemon_template.view.*

class PkAdapter(private val pokemon_list: List<Pokemon>) : RecyclerView.Adapter<PkAdapter.PkViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PkViewHolder = PkViewHolder(
        LayoutInflater.from(p0.context).inflate(R.layout.pokemon_template, p0, false)
    )


    override fun getItemCount(): Int = pokemon_list.size

    override fun onBindViewHolder(p0: PkViewHolder, p1: Int) {
        p0.bind(pokemon_list[p1])
    }


    class PkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Pokemon) = with(itemView) {
            pk_id.text = item.id.toString()
            pk_name.text = item.name
        }
    }
}