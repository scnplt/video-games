package dev.sertan.android.videogames.data.service

import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.model.GamesResponse
import kotlinx.coroutines.delay

internal class FakeGameService : GameService {
    private val gamesResponse: GamesResponse? by lazy {
        GamesResponse(
            listOf(
                Game(1, "a"),
                Game(2, "b"),
                Game(3, ""),
                Game(4, " "),
                Game(5, "."),
            )
        )
    }

    override suspend fun getGames(page: Int, key: String): GamesResponse? {
        delay(1000L)
        return gamesResponse

    }

    override suspend fun getGame(gameId: Int, key: String): Game? {
        delay(500L)
        return gamesResponse?.games?.find { it.id == gameId }
    }

}
