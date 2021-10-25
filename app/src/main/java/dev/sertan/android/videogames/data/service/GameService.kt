package dev.sertan.android.videogames.data.service

import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GameService {

    @GET("/api/games")
    suspend fun getGames(
        @Query("page") page: Int = 1,
        @Query("key") key: String = KEY,
    ): GamesResponse?

    @GET("/api/games/{gameId}")
    suspend fun getGame(
        @Path("gameId") gameId: Int,
        @Query("key") key: String = KEY
    ): Game?

    companion object {
        // Ödev olduğu için anahtarı gizlemedim.
        private const val KEY = "fbc7b8ca65d74ad99bc6de185320c32e"
        const val BASE_URL = "https://api.rawg.io/"
    }

}