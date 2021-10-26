package dev.sertan.android.videogames.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GamesResponse(
    @Json(name = "results")
    val games: List<Game>?
)
