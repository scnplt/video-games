package dev.sertan.android.videogames.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.repository.GameRepository
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(repo: GameRepository) : ViewModel() {
    val games: LiveData<List<Game>> = repo.getFavorites().asLiveData()
}
