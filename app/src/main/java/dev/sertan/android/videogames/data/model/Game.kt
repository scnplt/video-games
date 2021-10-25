package dev.sertan.android.videogames.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "game")
@JsonClass(generateAdapter = true)
internal data class Game(
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    @Ignore val description: String? = null,
    @Ignore val metacritic: Byte? = null,
    var released: String? = null,
    @Json(name = "background_image")
    var backgroundImageURL: String? = null,
    var rating: Float? = null,
    @Ignore val error: String? = null,
    var favorite: Boolean? = null
)
