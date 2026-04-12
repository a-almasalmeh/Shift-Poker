package service

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

/**
 * test klass for the [GameService.startGame] method
 *
 */
class StartGameTest
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
     * Verifies the successful start of a game with valid parameters
     */
    @Test
    fun `startGame with valid parameters `()
    {
        val testPlayerNames = listOf("Ahmad", "Hazem","Tarek" )
        val testRounds = 5

        gameService.startGame(testPlayerNames, testRounds)

        val game = rootService.currentGame
        assertNotNull(game)
        assert(game.players.size == 3 )
        assert(game.maxRounds == testRounds)
        assertEquals(34, game.drawStack.size)
        assertEquals(3, game.centerCards.size)

        for (player in game.players)
        {
            assertEquals(3,player.openHand.size)
            assertEquals(2, player.secretHand.size)
        }
        assertTrue(game.log.isNotEmpty())


    }

    /**
     * Verifies that starting a game when another game is already running throws an exception
     */
    @Test
    fun `starting game while game is running`()
    {
        gameService.startGame(listOf("Ahmad", "Hazem", "Tarek"), 4)

        assertFailsWith<IllegalStateException> { gameService.startGame(listOf("Ahmad", "Hazem"), 4) }
    }


    /**
     *Verifies that starting a game with less than 2 players throws an exception
     */
    @Test
    fun `starting game with invalid player number `()
    {
        assertFailsWith<IllegalArgumentException> { gameService.startGame(listOf("Ahmad"), 4) }
    }

    /**
     * Verifies that starting a game with less than 2 rounds throws an exception
     */
    @Test
    fun`starting game with invalid round number `()
    {
        assertFailsWith<IllegalArgumentException> { gameService.startGame(listOf("Ahmad", "Hazem"), 1) }
    }

    /**
     * Verifies that starting a game with an empty or blank player name throws an exception
     */
    @Test
    fun `starting game if a player name is empty `()
    {
        assertFailsWith<IllegalArgumentException> { gameService.startGame(listOf("Ahmad", "", "Tarek"), 3) }
    }

    /**
     * Verifies that starting a game with more than 4 players throws an exception
     */
    @Test
    fun `starting game if there are more than 4 players `()
    {
        assertFailsWith<IllegalArgumentException> { gameService.startGame(listOf("Ahmad", "Tarek", "A", "B", "c"), 3) }
    }

    /**
     * Verifies that starting a game with more than 7 max rounds throws an exception
     */
    @Test
    fun `starting game if rounds greater than 7 `()
    {
        assertFailsWith<IllegalArgumentException> { gameService.startGame(listOf("Ahmad", "Hazem"), 10) }
    }
}























