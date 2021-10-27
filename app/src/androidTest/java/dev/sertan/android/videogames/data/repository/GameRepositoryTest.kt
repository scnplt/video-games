package dev.sertan.android.videogames.data.repository

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.sertan.android.videogames.data.database.GameDAO
import dev.sertan.android.videogames.data.model.Game
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
internal class GameRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: GameRepository

    @Inject
    lateinit var gameDAO: GameDAO

    @Before
    fun setUp() = hiltRule.inject()

    @Test
    fun games_returnEmptyList() {
        runBlocking {
            val games = repo.games.first()
            Truth.assertThat(games).isEmpty()
        }
    }

    @Test
    fun games_returnList() {
        runBlocking {
            val games = arrayOf(Game(1), Game(2), Game(3), Game(4))
            gameDAO.insertGames(*games)
            val response = repo.games.first()
            Truth.assertThat(response).containsExactly(*games)
        }
    }

    @Test
    fun searchGame_returnEmptyList() {
        runBlocking {
            val response = repo.searchGame("x")
            Truth.assertThat(response).isEmpty()
        }
    }

    @Test
    fun searchGame_returnList() {
        runBlocking {
            val gameStart = Game(1, "asd")
            val gameEnd = Game(2, "das")
            val gameMid = Game(3, ".asd")
            val otherGame = Game(3, "xad")

            gameDAO.insertGames(gameStart, gameMid, gameEnd)
            val response = repo.searchGame("as")

            Truth.assertThat(response).containsExactly(gameStart, gameMid, gameEnd)
            Truth.assertThat(response).doesNotContain(otherGame)
        }
    }

    @Test
    fun getGame() {
        runBlocking {
            val game = Game(1)
            gameDAO.insertGames(game)

            val response = repo.getGame(1).first()
            Truth.assertThat(response.id).isEqualTo(game.id)
        }
    }

    @Test
    fun updateGame() {
        runBlocking {
            val game = Game(1, description = "", favorite = false)
            gameDAO.insertGames(game)

            repo.updateGame(game.apply { favorite = true })
            val response = repo.getGame(1).first()
            Truth.assertThat(response.favorite).isTrue()
        }
    }

    @Test
    fun getFavorites_returnEmptyList() {
        runBlocking {
            val response = repo.getFavorites().first()
            Truth.assertThat(response).isEmpty()
        }
    }

    @Test
    fun getFavorites() {
        runBlocking {
            val gameFavoriteTrue = Game(1, description = "", favorite = true)
            val gameFavoriteFalse = Game(2, description = "", favorite = false)
            val gameFavoriteNull = Game(2, description = "")
            gameDAO.insertGames(gameFavoriteTrue, gameFavoriteFalse, gameFavoriteFalse)

            val response = repo.getFavorites().first()
            Truth.assertThat(response).contains(gameFavoriteTrue)
            Truth.assertThat(response).doesNotContain(gameFavoriteFalse)
            Truth.assertThat(response).doesNotContain(gameFavoriteNull)
        }
    }

}
