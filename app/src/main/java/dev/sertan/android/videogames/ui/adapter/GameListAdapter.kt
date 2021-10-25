package dev.sertan.android.videogames.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.databinding.LayoutItemGameBinding
import dev.sertan.android.videogames.util.GameClickListener
import dev.sertan.android.videogames.util.GameDiffCallback

internal class GameListAdapter(private val gameClickListener: GameClickListener) :
    ListAdapter<Game, GameListAdapter.GameViewHolder>(GameDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutItemGameBinding>(
            inflater,
            R.layout.layout_item_game,
            parent,
            false
        )
        return GameViewHolder(binding, gameClickListener)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = currentList[position]
        holder.bind(game)
    }

    class GameViewHolder(
        private val binding: LayoutItemGameBinding,
        private val gameClickListener: GameClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            binding.game = game
            binding.listener = gameClickListener
            binding.executePendingBindings()
        }

    }

}

