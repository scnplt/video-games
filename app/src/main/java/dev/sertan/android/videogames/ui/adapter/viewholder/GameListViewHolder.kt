package dev.sertan.android.videogames.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.databinding.LayoutItemGameBinding
import dev.sertan.android.videogames.util.GameClickListener

internal class GameListViewHolder(
    private val binding: LayoutItemGameBinding,
    private val gameClickListener: GameClickListener
) : GameViewHolder(binding.root) {

    override fun bind(game: Game) {
        binding.game = game
        binding.listener = gameClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun createInstance(
            parent: ViewGroup,
            gameClickListener: GameClickListener
        ): GameListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<LayoutItemGameBinding>(
                inflater,
                R.layout.layout_item_game,
                parent,
                false
            )
            return GameListViewHolder(binding, gameClickListener)
        }
    }


}
