package dev.sertan.android.videogames.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.sertan.android.videogames.data.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
internal abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDAO(): GameDAO
}
