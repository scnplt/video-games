package dev.sertan.android.videogames.data.repository

import dev.sertan.android.videogames.data.database.GameDAO
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.service.GameService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GameRepository @Inject constructor(
    private val networkDataSource: GameService,
    private val localDataSource: GameDAO
) {
    val games: Flow<List<Game>> by lazy { localDataSource.getAllGames() }

    init {
        checkNewGames()
    }

    private fun checkNewGames() {
        CoroutineScope(Dispatchers.IO).launch {
            val gamesFromNetwork = networkDataSource.getGames()?.games ?: return@launch
            val newGames = gamesFromNetwork.filter { gameFromNetwork ->
                games.firstOrNull()
                    ?.let { it.find { gameFromLocal -> gameFromLocal.id == gameFromNetwork.id } == null }
                    ?: return@launch
            }
            localDataSource.insertGames(*newGames.toTypedArray())
        }
    }

    suspend fun searchGame(gameName: String): List<Game> {
        return localDataSource.searchGame("%$gameName%")
    }

    fun getGame(gameId: Int): Flow<Game> {
        return localDataSource.getGame(gameId).map {
            if (it.description != null) return@map it

            val gameFromNetwork = networkDataSource.getGame(gameId) ?: return@map Game()
            gameFromNetwork.apply {
                favorite = false
                localDataSource.insertGames(this)
            }
        }
    }

    suspend fun updateGame(game: Game) = localDataSource.updateGame(game)

}
