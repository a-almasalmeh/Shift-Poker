package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

/**
 * test class for [PlayerService.passTurn] method
 */
class PassTurnTest
{
    private lateinit var rootService: RootService
    private lateinit var playerService: PlayerService



    /**
     * sets up a [RootService] and a [PlayerService] before each test run
     */
    @BeforeEach
    fun setup()
    {
        rootService = RootService()
        playerService = rootService.playerService

    }

    /**
     * Verifies that passing the turn successfully calls nextPlayer and changes the current player
     */
    @Test
    fun `passTurn works successfully`()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)

        val game = rootService.currentGame
        checkNotNull(game)

        game.currentPlayer = 0
        game.roundCount = 1

        playerService.passTurn()

        assertEquals(1,game.currentPlayer)
    }


    /**
     * Verifies that the method throws an IllegalStateException if no game is running
     */
    @Test
    fun `calling passTurn while the game is not running`()
    {
        assertFailsWith<IllegalStateException> { playerService.passTurn() }
    }

    /**
     * Verifies that the method throws an IllegalStateException if the player has already used all their actions
     */
    @Test
    fun `calling passTurn if the player has no Actions left`()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)
        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 2

        assertFailsWith<IllegalStateException> { playerService.passTurn() }
    }


}