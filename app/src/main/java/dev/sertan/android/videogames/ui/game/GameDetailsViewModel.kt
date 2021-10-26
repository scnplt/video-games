package dev.sertan.android.videogames.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.repository.GameRepository
import dev.sertan.android.videogames.data.service.analytics.AnalyticsService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GameDetailsViewModel @Inject constructor(
    private val repo: GameRepository,
    private val analytics: AnalyticsService
) : ViewModel() {

    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game> get() = _game

    fun loadGame(gameId: Int) {
        viewModelScope.launch {
            repo.getGame(gameId).collect { _game.postValue(it) }
        }
    }

    fun changeFavoriteStatus() {
        viewModelScope.launch {
            game.value?.apply { favorite = favorite?.not() ?: true }
                ?.let { repo.updateGame(it) } ?: return@launch

            analytics.logGameFavoriteStatusChange(
                gameId = game.value!!.id ?: -1,
                gameName = "${game.value?.name}",
                favorite = game.value!!.favorite ?: false
            )
        }
    }

}
