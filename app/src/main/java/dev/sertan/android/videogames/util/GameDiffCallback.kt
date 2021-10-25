package dev.sertan.android.videogames.util

import androidx.recyclerview.widget.DiffUtil
import dev.sertan.android.videogames.data.model.Game

internal object GameDiffCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem
}
