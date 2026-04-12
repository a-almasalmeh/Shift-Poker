package service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.*

/**
 * tests for the [GameService.endGame] method
 */
class EndGameTest
{
    private lateinit var rootService: RootService
    private lateinit var gameService: GameService

    /**
     * sets up a [RootService] and a [GameService] before each test run
     *
     */
    @BeforeEach
    fun setUp ()
    {
        rootService = RootService()
        gameService = rootService.gameService
    }


    /**
     * Verifies that calling the method without an active game throws an exception
     */
    @Test
    fun `calling endGame and no game is running `()
    {
        assertFailsWith<IllegalStateException> { gameService.endGame() }
    }

    /**
     * Verifies that endGame successfully calculates the hand ranks for all players and that the method does not crash
     */
    @Test
    fun `calling endGame and game is running `()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad"),4)

        assertDoesNotThrow { gameService.endGame() }

    }

    /**
     * Verifies that executing the [GameService.endGame] method triggers refreshAfterEndGame()
     */
    @Test
    fun `swapOne triggers refresh`()
    {
        val testUI = TestRefreshable()
        gameService.addRefreshable(testUI)
        gameService.startGame(listOf("Ahmad", "Ahmad"),4)
        gameService.endGame()

        assertTrue { testUI.refreshAfterEndGameCalled }

    }
}