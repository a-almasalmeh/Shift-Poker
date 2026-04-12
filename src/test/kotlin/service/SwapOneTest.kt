package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*


/**
 * test class for [PlayerService.swapOne] method
 * Ensures that a single card is correctly swapped between a player's hand and the center
 */
class SwapOneTest
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

        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)
    }

    /**
     * Tests if a single card is correctly exchanged and the action count increases
     */
    @Test
    fun `swapOne works successfully `()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        val testPlayer = game.players[0]
        val originalHandCard = testPlayer.openHand[0]
        val originalCenterCard = game.centerCards[0]

        playerService.swapOne(0, 0)

        assertEquals(originalCenterCard, testPlayer.openHand[0])
        assertEquals(originalHandCard, game.centerCards[0])
        assertEquals(1, game.actionCount)
    }

    /**
     * Verifies that the turn automatically to the next player after 2 actions
     */
    @Test
    fun `next player's turn after second action`()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        playerService.swapOne(0,0)
        assertEquals(0,game.currentPlayer)
        assertEquals(1, game.actionCount)

        playerService.swapOne(1,1)
        assertEquals(1,game.currentPlayer)
        assertEquals(0,game.actionCount)
    }


    /**
     * Verifies that the game automatically ends after the last action of the last round
     */
    @Test
    fun `game ends after last action of the last round `()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 1
        game.roundCount = 4
        game.currentPlayer = 1

        playerService.swapOne(1,1)



        // test dass nextPlayer nicht augerufen wird
        assertEquals(1,game.currentPlayer)

    }

    /**
     * test if the method prevents a 3rd action
     */
    @Test
    fun `calling swapOne and no actions are left` ()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 2

        assertFailsWith<IllegalStateException> { playerService.swapOne(0, 0) }
    }

    /**
     * test swapOne throws IllegalArgumentException if the indices are out of bounds
     */
    @Test
    fun `indices are out of bounds` ()
    {
        assertFailsWith<IllegalArgumentException> { playerService.swapOne(0,-1) }
        assertFailsWith<IllegalArgumentException> { playerService.swapOne(1, 8) }
        assertFailsWith<IllegalArgumentException> { playerService.swapOne(-1, 0) }
        assertFailsWith<IllegalArgumentException> { playerService.swapOne(8, 0) }
    }

}