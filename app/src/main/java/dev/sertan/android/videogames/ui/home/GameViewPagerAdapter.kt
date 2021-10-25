package dev.sertan.android.videogames.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.databinding.LayoutItemGameImageBinding
import dev.sertan.android.videogames.util.GameClickListener
import dev.sertan.android.videogames.util.GameDiffCallback

internal class GameViewPagerAdapter(private val gameClickListener: GameClickListener) :
    ListAdapter<Game, GameViewPagerAdapter.GameViewHolder>(GameDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutItemGameImageBinding>(
            inflater,
            R.layout.layout_item_game_image,
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = currentList[position]
        holder.bind(game, gameClickListener)
    }

    class GameViewHolder(private val binding: LayoutItemGameImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game, gameClickListener: GameClickListener?) {
            binding.game = game
            binding.listener = gameClickListener
            binding.executePendingBindings()
        }

    }

}
