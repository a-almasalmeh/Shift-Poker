package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

/**
 * test class for [PlayerService.shiftLeft] method
 * Verifies that the leftmost center card is discarded, a new card is drawn from the right
 */
class ShiftLeftTest
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
     * Verifies the successful execution of a left shift
     * Ensures that the leftmost center card is moved to the discard stack,
     * a new card is drawn and placed at the rightmost center position
     */
    @Test
    fun `shiftLeft works successfully ` ()
    {

        val game = rootService.currentGame
        checkNotNull(game)
        game.actionCount = 0

        val initialFirstCenterCard = game.centerCards.first()
        val initialLastDrawStackCard = game.drawStack.last()

        val initialDrawStackSize = game.drawStack.size
        val initialDiscardStackSize = game.discardStack.size

        playerService.shiftLeft()

        assertEquals(initialFirstCenterCard, game.discardStack.last())
        assertEquals(initialLastDrawStackCard, game.centerCards.last())

        assertEquals(initialDrawStackSize - 1, game.drawStack.size)
        assertEquals(initialDiscardStackSize + 1, game.discardStack.size)

        assertEquals(1, game.actionCount)
    }


    /**
     * Verifies that the turn automatically passes to the next player
     * when the current player uses their second action
     */
    @Test
    fun `shiftLeft triggers nextPlayer after the second action` ()
    {
        val game = rootService.currentGame
        checkNotNull(game)

        game.currentPlayer = 0
        game.actionCount = 1

        playerService.shiftLeft()

        assertEquals(1, game.currentPlayer)
        assertEquals(0, game.actionCount)

    }
    /**
     * Verifies that shifting left is blocked if the player has already used all of their action
     */
    @Test
    fun `shiftLeft throws IllegalStateException if no actions are left`()
    {
        val game = rootService.currentGame
        game?.actionCount = 2

        assertFailsWith<IllegalStateException> { playerService.shiftLeft() }
    }










}