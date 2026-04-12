package entity

/**
 * Represent a single playing card in the Shift Poker game.
 *
 * @property suit the suit of the card (e.g. CLUBS,  SPADES, HEARTS, DIAMONDS)
 *
 * @property value the value of the card (Two, Ten, Jack)
 *
 *
 */
data class Card(val suit: CardSuit, val value: CardValue)
