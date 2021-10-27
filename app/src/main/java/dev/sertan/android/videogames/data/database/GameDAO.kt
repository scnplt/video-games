package dev.sertan.android.videogames.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.sertan.android.videogames.data.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
internal interface GameDAO {

    @Query("SELECT * FROM game WHERE :gameId = id")
    fun getGame(gameId: Int): Flow<Game>

    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE name LIKE :value")
    suspend fun searchGame(value: String): List<Game>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGames(vararg games: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("SELECT * FROM game WHERE favorite=1")
    fun getFavorites(): Flow<List<Game>>

}
