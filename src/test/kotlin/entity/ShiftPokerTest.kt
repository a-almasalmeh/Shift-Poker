package entity

import kotlin.test.*

/**
 * Test class for the [ShiftPoker] entity
 * Verifies that the main game state container correctly holds players, game statistics
 * and the card stacks (draw, discard, center)
 *
 *
 */
class ShiftPokerTest
{
    private val player1: Player = Player("Ahmad")
    private val player2: Player = Player("Hazem")

    private val roundCountTest: Int = 5


    private val card1 = Card(CardSuit.DIAMONDS, CardValue.TWO)
    private val card2 = Card(CardSuit.HEARTS, CardValue.THREE)
    private val card3 = Card(CardSuit.SPADES, CardValue.FOUR)
    private val card4 = Card(CardSuit.CLUBS, CardValue.FIVE)
    private val card5 = Card(CardSuit.DIAMONDS, CardValue.SIX)
    private val card6 = Card(CardSuit.HEARTS, CardValue.SEVEN)
    private val card7 = Card(CardSuit.SPADES, CardValue.EIGHT)
    private val card8 = Card(CardSuit.CLUBS, CardValue.QUEEN)

    private val testLog: MutableList<String> = mutableListOf("game started with $player1 and $player2")
    private val discardStackTest: MutableList<Card> = mutableListOf(card1, card4, card3, card2)
    private val drawStackTest: MutableList<Card> = mutableListOf(card1, card8, card5, card6)
    private val centerCardsTest: MutableList<Card> = mutableListOf(card7, card4, card1)

    /**
     * Verifies that a [ShiftPoker] instance correctly stores game state data
     */
    @Test
    fun shiftPokerGameTest()
    {
        val shiftPokerTestGame = ShiftPoker(mutableListOf(player1, player2), roundCountTest)
        shiftPokerTestGame.log = testLog
        shiftPokerTestGame.roundCount = roundCountTest
        shiftPokerTestGame.discardStack.addAll(discardStackTest)
        shiftPokerTestGame.drawStack.addAll(drawStackTest)
        shiftPokerTestGame.centerCards.addAll(centerCardsTest)

        assertEquals(testLog, shiftPokerTestGame.log)
        assertEquals(5, shiftPokerTestGame.roundCount)
        assertEquals(discardStackTest, shiftPokerTestGame.discardStack)
        assertEquals(drawStackTest, shiftPokerTestGame.drawStack)
        assertEquals(centerCardsTest, shiftPokerTestGame.centerCards)
    }
}