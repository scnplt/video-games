package dev.sertan.android.videogames.data.repository

import dev.sertan.android.videogames.data.database.GameDAO
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.service.GameService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GameRepository @Inject constructor(
    private val networkDataSource: GameService,
    private val localDataSource: GameDAO
) {
    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }
    val games: Flow<List<Game>> = localDataSource.getAllGames()

    init {
        checkNewGames()
    }

    private fun checkNewGames() {
        coroutineScope.launch {
            val gamesFromNetwork = networkDataSource.getGames()?.games ?: return@launch
            val newGames = gamesFromNetwork.filter { gameFromNetwork ->
                games.firstOrNull()
                    ?.let { it.find { gameFromLocal -> gameFromLocal.id == gameFromNetwork.id } == null }
                    ?: return@launch
            }
            localDataSource.insertGames(*newGames.toTypedArray())
        }
    }

    suspend fun searchGame(gameName: String): List<Game> = localDataSource.searchGame("%$gameName%")

}
