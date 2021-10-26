package dev.sertan.android.videogames.ui.adapter.viewholder

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import dev.sertan.android.videogames.data.model.Game

internal abstract class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(game: Game)
}
