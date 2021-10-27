package dev.sertan.android.videogames.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.module.GlideApp

@BindingAdapter("loadImg")
internal fun ImageView.loadImage(url: String?) {
    GlideApp.with(context).load(url).into(this)
}

@BindingAdapter("games")
internal fun bindGames(view: View, games: List<Game>?) {
    when (view) {
        is ViewPager2 -> (view.adapter as? GameListAdapter)?.submitList(games)
        is RecyclerView -> (view.adapter as? GameListAdapter)?.submitList(games)
    }
}

@BindingAdapter("isActive")
internal fun bindIsActive(view: ImageView, isActive: Boolean?) {
    var state = -android.R.attr.state_active
    if (isActive == true) state *= -1

    view.setImageState(intArrayOf(state), false)
}
