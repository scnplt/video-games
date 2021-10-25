package dev.sertan.android.videogames.util

import dev.sertan.android.videogames.data.model.Game

internal fun interface GameClickListener {
    fun onClickGame(game: Game)
}
