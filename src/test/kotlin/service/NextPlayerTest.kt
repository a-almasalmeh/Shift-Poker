package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*


/**
 * Test class for the [GameService.nextPlayer] method.
 */
class NextPlayerTest
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
     * verifies that calling nextPlayer without an active game throws an exception
     */
    @Test
    fun `calling nextPlayer if no game is running`()
    {
        assertFailsWith<IllegalStateException> { gameService.nextPlayer() }
    }

    /**
     * Verifies that the turn correctly advances to the next player and the action count is reset to the 0
     */
    @Test
    fun `nextPlayer works and resets action count`()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad"), 4)
        val game = rootService.currentGame
        assertNotNull(game)

        game.actionCount = 2
        game.currentPlayer = 0

        gameService.nextPlayer()

        assertEquals(1, game.currentPlayer)
        assertEquals(0,game.actionCount)
        assertEquals(1, game.roundCount)
    }

    /**
     * Verifies that the turn wraps around to the first player after the last player and the round count increments by 1
     */
    @Test
    fun `again to first player and increment round count by one `()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad", "Tarek"), 4)
        val game = rootService.currentGame
        assertNotNull(game)

        game.roundCount = 2
        game.currentPlayer = 2

        gameService.nextPlayer()

        assertEquals(0, game.currentPlayer)
        assertEquals(3, game.roundCount)
    }

    /**
     * Verifies that the game properly triggers [GameService.endGame] when the maximum number of rounds is exceeded
     *
     */
    @Test
    fun `next player when max round exceeded` ()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad", "Tarek"), 4)
        val game = rootService.currentGame
        assertNotNull(game)

        game.roundCount = 4
        game.currentPlayer = 2
        game.actionCount = 2

        gameService.nextPlayer()

        assertEquals(5, game.roundCount)

    }
}






















