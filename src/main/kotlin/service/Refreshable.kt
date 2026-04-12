package service

/**
 * This interface provides a mechanism for the service layer classes to communicate
 * (usually to the GUI classes) that certain changes have been made to the entity
 * layer, so that the user interface can be updated accordingly.
 *
 * Default (empty) implementations are provided for all methods, so that implementing
 * GUI classes only need to react to events relevant to them.
 *
 * @see AbstractRefreshingService
 */
interface Refreshable
{
    /**
     * Called after a player swaps a single card between their hand and the center
     * @param handIndex The index of the card in the player's open hand
     * @param centerIndex The index of the card in the center cards
     */
    fun refreshAfterSwapOne(handIndex: Int, centerIndex: Int){}

    /**
     * Called after a player swaps all three of their open hand cards with the center cards
     */
    fun refreshAfterSwapThree(){}

    /**
     * Called after a new game has been successfully initialized and started
     */
    fun refreshAfterStartGame(){}

    /**
     * Called when the game ends to display the final results and winner
     * @param handRanks A list containing the final [HandRank] for each player
     */
    fun refreshAfterEndGame(handRanks: List<HandRank>) {}

    /**
     * Called after the center cards have been shifted one position to the right
     */
    fun refreshAfterShiftRight(){}

    /**
     * Called after the center cards have been shifted one position to the left
     */
    fun refreshAfterShiftLeft(){}

    /**
     * Called after the turn has passed to the next player
     */
    fun refreshAfterNextPlayer(){}


}