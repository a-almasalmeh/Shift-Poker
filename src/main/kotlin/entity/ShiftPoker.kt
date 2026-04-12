package entity

import service.HandRank

/**
 * Represent the main game state and the physical table of a Shift Pocker game, and holds all information about the game
 *
 * @property players The list of players currently participating in the game (2 to 4)
 * @property maxRounds The total number of rounds to be played bevor the game ends
 * @property log A history of all actions and events that occurred during the game
 * @property roundCount The current round number the game is in (2 to 7)
 * @property actionCount The number of action already taken in the current turn (max. 2)
 * @property currentPlayer The index of the player whose turn currently is
 * @property discardStack The pile of cards that have been discarded (for the beginning empty)
 * @property drawStack The pile of cards from which new cards are drawn
 * @property centerCards The cards placed in the center
 */
data class ShiftPoker(val players: MutableList<Player>, val maxRounds: Int)
{
    var log: MutableList<String> = mutableListOf()
    var roundCount: Int = 1
    var actionCount: Int = 0
    var currentPlayer: Int = 0

    val discardStack: MutableList<Card> = mutableListOf()
    val drawStack: MutableList<Card> = mutableListOf()
    val centerCards: MutableList<Card> = mutableListOf()
}
