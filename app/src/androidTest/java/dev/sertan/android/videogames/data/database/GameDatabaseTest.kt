package dev.sertan.android.videogames.data.database

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.sertan.android.videogames.data.model.Game
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
internal class GameDatabaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gameDAO: GameDAO

    @Before
    fun setUp() = hiltRule.inject()

    @Test
    fun getGame_invalidGameId_returnNull() = runBlocking {
        val response = gameDAO.getGame(-1)
        val game = response.firstOrNull()
        Truth.assertThat(game).isNull()
    }

    @Test
    fun getGame_validGameId_returnGame() = runBlocking {
        val game = Game(-1)
        gameDAO.insertGames(game)

        val response = gameDAO.getGame(game.id!!)
        Truth.assertThat(response.firstOrNull()).isEqualTo(game)
    }

    @Test
    fun getAllGames_returnEmptyList() = runBlocking {
        val response = gameDAO.getAllGames().first()
        Truth.assertThat(response).isEmpty()
    }

    @Test
    fun getAllGames_returnFullList() = runBlocking {
        val games = arrayOf(Game(1), Game(2), Game(3))
        gameDAO.insertGames(*games)

        val response = gameDAO.getAllGames().first()
        Truth.assertThat(response).containsExactly(*games)
        return@runBlocking
    }

    @Test
    fun searchGame_returnEmptyList() = runBlocking {
        val response = gameDAO.searchGame("x")
        Truth.assertThat(response).isEmpty()
    }

    @Test
    fun searchGame() = runBlocking {
        val games = arrayOf(
            Game(0, name = "asb"),
            Game(1, name = "bgh"),
            Game(2, name = "jbl")
        )
        gameDAO.insertGames(*games)

        Truth.assertThat(gameDAO.searchGame("%b%")).containsExactly(*games)
        Truth.assertThat(gameDAO.searchGame("%gh%")).contains(games[1])
        Truth.assertThat(gameDAO.searchGame("%jb%")).contains(games[2])
    }

    @Test
    fun insertGames() = runBlocking {
        val game1 = Game(1)
        val game2 = Game(2)
        val game3 = Game(3)
        gameDAO.insertGames(game1)
        gameDAO.insertGames(game2, game3)

        val response = gameDAO.getAllGames().first()
        Truth.assertThat(response).containsExactly(game1, game2, game3)
        return@runBlocking
    }

    @Test
    fun updateGame_invalidId() = runBlocking {
        val game = Game(1)
        gameDAO.insertGames(game)

        game.favorite = true
        gameDAO.updateGame(game.copy(2))

        val response = gameDAO.getGame(game.id!!)
        Truth.assertThat(response.first()).isNotNull()
    }

    @Test
    fun updateGame_validId() = runBlocking {
        val game = Game(1, favorite = true)
        gameDAO.insertGames(game)

        game.favorite = false
        gameDAO.updateGame(game)

        val response = gameDAO.getGame(game.id!!)
        Truth.assertThat(response.first()).isEqualTo(game)
    }

}
