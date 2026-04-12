package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*


/**
 * test class for [PlayerService.shiftRight] method
 * Verifies that the rightmost center card is discarded, a new card is drawn from the left
 */

class ShiftRightTest
{
    private lateinit var rootService: RootService
    private lateinit var playerService: PlayerService


    /**
     * sets up a [RootService] and a [PlayerService] before each test run
     */
    @BeforeEach
    fun setup() {
        rootService = RootService()
        playerService = rootService.playerService
        rootService.gameService.startGame(listOf("Ahmad", "Ahmad", "Ahmad"), 4)
    }

    /**
     * Verifies the successful execution of a right shift
     * Ensures that the rightmost center card is moved to the discard stack,
     * a new card is drawn and placed at the leftmost center position
     */
    @Test
    fun `shiftRight works successfully ` ()
    {

        val game = rootService.currentGame
        checkNotNull(game)
        game.actionCount = 0

        val initialLastCenterCard = game.centerCards.last()
        val initialLastDrawStackCard = game.drawStack.last()

        val initialDrawStackSize = game.drawStack.size
        val initialDiscardStackSize = game.discardStack.size

        playerService.shiftRight()

        assertEquals(initialLastCenterCard, game.discardStack.last())
        assertEquals(initialLastDrawStackCard, game.centerCards.first())

        assertEquals(initialDrawStackSize - 1, game.drawStack.size)
        assertEquals(initialDiscardStackSize + 1, game.discardStack.size)

        assertEquals(1, game.actionCount)
    }

    /**
     * Verifies that the turn automatically passes to the next player
     * when the current player uses their second action
     */
    @Test
    fun `shiftRight triggers nextPlayer after the second action` ()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        game.currentPlayer = 0
        game.actionCount = 1

        playerService.shiftRight()

        assertEquals(1, game.currentPlayer)
        assertEquals(0, game.actionCount)

    }

    /**
     * Verifies that shifting right is blocked if the player has already used all of their action
     */
    @Test
    fun `shiftLeft throws IllegalStateException if no actions are left`()
    {
        val game = rootService.currentGame
        game?.actionCount = 2

        assertFailsWith<IllegalStateException> { playerService.shiftRight() }
    }
}