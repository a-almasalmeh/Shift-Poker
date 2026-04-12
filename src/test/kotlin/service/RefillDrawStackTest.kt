package service

import entity.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*


/**
 * test class for the [GameService.refillDrawStack] method
 * Verifies that the draw stack is correctly refilled from the discard stack
 */
class RefillDrawStackTest {
    private lateinit var rootService: RootService
    private lateinit var gameService: GameService

    /**
     * sets up a [RootService] and a [GameService] before each test run
     *
     */
    @BeforeEach
    fun setUp() {
        rootService = RootService()
        gameService = rootService.gameService
    }

    /**
     * Verifies that calling the method without an active game throws an exception
     */
    @Test
    fun `calling refillDrawStack if no game is running`()
    {
        assertFailsWith<IllegalStateException> { gameService.refillDrawStack() }

    }

    /**
     * Verifies that an exception is thrown if the draw stack is not empty
     */
    @Test
    fun `calling refillDrawStack while drawStack is not empty`()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad"), maxRounds = 5)
        assertFailsWith<IllegalStateException> { gameService.refillDrawStack() }
    }


    /**
     * Verifies that an empty draw stack is correctly refilled using all cards from the discard stack
     * and that the discard stack is empty after that
     */
    @Test
    fun `removing all cards from discard stack to draw stack`()
    {
        gameService.startGame(listOf("Ahmad", "Ahmad"), maxRounds = 5)
        val game = rootService.currentGame
        checkNotNull(game)

        game.drawStack.clear()

        game.discardStack.addAll(listOf(Card(CardSuit.DIAMONDS, CardValue.SIX),
            Card(CardSuit.HEARTS, CardValue.ACE), Card(CardSuit.HEARTS, CardValue.ACE)))

        gameService.refillDrawStack()

        assertEquals(3, game.drawStack.size)
        assert(game.discardStack.isEmpty())

    }

}