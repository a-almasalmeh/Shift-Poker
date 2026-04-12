package service

import entity.Card
import org.junit.jupiter.api.BeforeEach
import kotlin.test.*


/**
 * test class for [PlayerService.swapThree] method
 * Verifies that all three cards are correctly swapped between the current player's open hand and the center cards
 */
class SwapThreeTest
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
     * Verifies that the method throws an IllegalStateException if no game is running
     */
    @Test
    fun `calling swapThree and game is not started `()
    {
        assertFailsWith<IllegalStateException> { playerService.swapThree() }
    }




    /**
     * Verifies that all three cards are exchanged and the action count increases
     */
    @Test
    fun `calling swapThree successfully`()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)

        val game = rootService.currentGame
        checkNotNull(game)

        val testOpenHand = mutableListOf<Card>()
        testOpenHand.addAll(game.players[game.currentPlayer].openHand)

        val testCenterHand = mutableListOf<Card>()
        testCenterHand.addAll(game.centerCards)

        playerService.swapThree()

        assertEquals(testOpenHand,game.centerCards)
        assertEquals(testCenterHand,game.players[game.currentPlayer].openHand)
        assertEquals(1,game.actionCount)
    }




    /**
     * Verifies that the turn automatically passes to the next player after 2 actions
     */
    @Test
    fun `next player after the second action`()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)

        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 1

        playerService.swapThree()

        assertEquals(1,game.currentPlayer)
        assertEquals(0,game.actionCount)
    }



    /**
     * Verifies that the game automatically ends after the last action of the last round
     */
    @Test
    fun `game ends after last action of the last round `()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)
        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 1
        game.roundCount = 4
        game.currentPlayer = 1

        playerService.swapThree()



        // test dass nextPlayer nicht augerufen wird
        assertEquals(1,game.currentPlayer)
    }

    /**
     * test if swapThree prevents a 3rd action
     */
    @Test
    fun `calling swapOne and no actions are left` ()
    {
        rootService.gameService.startGame(listOf("Ahmad", "Tarek"), 4)

        val game = rootService.currentGame
        checkNotNull(game)

        game.actionCount = 2

        assertFailsWith<IllegalStateException> { playerService.swapThree() }
    }


}