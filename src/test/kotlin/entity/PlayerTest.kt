package entity

import kotlin.test.*

/**
 * Test class for the [Player] entity
 * Ensures that a player is created with the correct name and their card hands (open and secret)
 *
 */
class PlayerTest
{

    private val inputName: String = "Ahmad"

    private val card1: Card = Card(CardSuit.DIAMONDS, CardValue.TEN)
    private val card2: Card = Card(CardSuit.HEARTS, CardValue.ACE)
    private val card3: Card = Card(CardSuit.CLUBS, CardValue.TEN)
    private val card4: Card = Card(CardSuit.SPADES, CardValue.KING)
    private val card5: Card = Card(CardSuit.HEARTS, CardValue.SIX)

    private val openHandTest: MutableList <Card> = mutableListOf(card1, card2,card3)
    private val secretHandTest: MutableList <Card> = mutableListOf(card4, card5)


    /**
     *
     * Verifies the initialization of a [Player] object
     * checks if the name is stored correctly and if lists of cards can be assigned to the open and secret hand
     */
    @Test
    fun createPlayerTest()
    {
        val testPlayer: Player = Player(inputName)

        testPlayer.openHand.addAll(openHandTest)
        testPlayer.secretHand.addAll(secretHandTest)

        assertEquals(inputName, testPlayer.name)
        assertEquals(openHandTest, testPlayer.openHand)
        assertEquals(secretHandTest, testPlayer.secretHand)

    }


}