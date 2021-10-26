package dev.sertan.android.videogames.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.module.GlideApp
import dev.sertan.android.videogames.ui.home.GameViewPagerAdapter

@BindingAdapter("loadImg")
internal fun ImageView.loadImage(url: String?) {
    GlideApp.with(context).load(url).into(this)
}

@BindingAdapter("games")
internal fun bindGames(view: ViewPager2, games: List<Game>?) {
    (view.adapter as? GameViewPagerAdapter)?.submitList(games)
}

@BindingAdapter("games")
internal fun bindGames(view: RecyclerView, games: List<Game>?) {
    (view.adapter as? GameListAdapter)?.submitList(games)
}

@BindingAdapter("isActive")
internal fun bindIsActive(view: ImageView, isActive: Boolean?) {
    var state = -android.R.attr.state_active
    if (isActive == true) state *= -1

    view.setImageState(intArrayOf(state), false)
}
