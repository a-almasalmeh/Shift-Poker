package entity

import kotlin.test.*

/**
 * Test class for the [Card] entity
 * Verifies that card objects are initialized correctly with the given suit and value
 *
 */
class CardTest
{
    private val inputSuit1: CardSuit = CardSuit.HEARTS
    private val inputSuit2: CardSuit = CardSuit.DIAMONDS

    private val inputValue1: CardValue = CardValue.TEN
    private val inputValue2: CardValue = CardValue.KING


    private val testCard1: Card = Card(inputSuit1, inputValue1)
    private val testCard2: Card = Card(inputSuit2, inputValue2)

    @Test
            /**
             *
             * Checks multiple combinations (HEARTS TEN, DIAMONDS KING)
             */

    fun createCardTest ()
    {

        assertEquals(CardSuit.HEARTS, testCard1.suit)
        assertEquals(CardValue.TEN, testCard1.value)

        assertEquals(CardSuit.DIAMONDS, testCard2.suit)
        assertEquals(CardValue.KING, testCard2.value)

    }
}