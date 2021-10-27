package dev.sertan.android.videogames.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.ui.adapter.viewholder.GameViewHolder
import dev.sertan.android.videogames.util.GameDiffCallback

internal class GameListAdapter<VH : GameViewHolder>(private val getViewHolder: (ViewGroup) -> VH) :
    ListAdapter<Game, VH>(GameDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = getViewHolder(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        val game = currentList[position]
        holder.bind(game)
    }

}

