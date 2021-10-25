package dev.sertan.android.videogames.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GamesResponse(
    val count: Int?,
    @Json(name = "next")
    val nextGamesURL: String?,
    @Json(name = "previous")
    val previousGamesURL: String?,
    @Json(name = "results")
    val games: List<Game>?,
    val error: String?
)
