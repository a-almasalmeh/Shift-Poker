package entity


/**
 * @property name The chosen name of the player
 *
 * @property openHand The list of cards the player holds face-up. Usually 3 cards (visible for all players)
 * @property secretHand The list of the cards the player holds face-down (only for the player visible)
 *
 * */

data class Player(val name: String)
{
    val openHand: MutableList<Card> = mutableListOf()
    val secretHand: MutableList<Card> = mutableListOf()
}
