package service

import entity.*

/**
 * service responsible for the general flow of the game, including starting a game,
 * managing the turns handling the card stacks and ending the game
 *
 */
class GameService(val rootService: RootService): AbstractRefreshingService()
{

    /**
     * initializing and starting a new game
     * creates the players, deals the initial hands, sets up the center cards, and prepares the draw stack
     * @param playerNames A list of names for the participating players. Must contain between 2 and 4 names
     * @param maxRounds The maximum number of rounds to play must be between 2 and 7
     * @throws IllegalStateException if the game is already running
     * @throws IllegalArgumentException if maxRounds out of bounds
     */
    fun startGame(playerNames: List<String>, maxRounds: Int)
    {
        check(rootService.currentGame == null) { "Game is already running" }
        require(playerNames.size in 2..4){ "2 to 4 Players !!!" }
        require(playerNames.all { !it.isBlank() }){"playerName should be not empty"}
        require(maxRounds in 2..7) {"maxRounds must be between 2 and 7"}

        val players: MutableList<Player> = mutableListOf()
        for (name in playerNames)
        {
            players.add(Player(name))
        }

        val newGame = ShiftPoker(players, maxRounds)
        rootService.currentGame = newGame

        createDrawStack()
        newGame.discardStack.clear()

        for (player in newGame.players)
        {
            player.openHand.addAll(mutableListOf(newGame.drawStack.removeLast(),
                newGame.drawStack.removeLast(), newGame.drawStack.removeLast()))

            player.secretHand.addAll(mutableListOf(newGame.drawStack.removeLast(), newGame.drawStack.removeLast()))

        }

        newGame.centerCards.addAll(mutableListOf(newGame.drawStack.removeLast(),
            newGame.drawStack.removeLast(), newGame.drawStack.removeLast()))

        newGame.log.add("Game started with ${players.size} players for $maxRounds rounds")

        onAllRefreshables { refreshAfterStartGame() }

    }


    /**
     * creates a new card deck, places it into the draw stack and shuffles it
     * @throws IllegalStateException if the game is not running, or if the draw stack is already initialized
     */
    private fun createDrawStack()
    {
        val game = rootService.currentGame
        checkNotNull(game){"the game is not running"}
        check(game.drawStack.isEmpty()) { "The draw stack is already initialized!" }
        for (suit in CardSuit.entries)
        {
            for (value in CardValue.entries)
            {
                game.drawStack.add(Card(suit, value))
            }
        }
        game.drawStack.shuffle()
        game.discardStack.clear()
    }


    /**
     * advances the turn to the next player
     * reset the actionCount for the next player and increments the roundCount if a full circle is completed
     * ends the game if the maximum number of rounds exceeded
     * @throws IllegalStateException if the game is not running
     */
    fun nextPlayer()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not running"}

        game.currentPlayer = (game.currentPlayer + 1) % (game.players.size)

        game.actionCount = 0

        if (game.currentPlayer == 0)
        {
            game.roundCount++
        }

        if (game.roundCount > game.maxRounds)
        {
            endGame()
        }
        onAllRefreshables { refreshAfterNextPlayer() }
    }


    /**
     * refills an empty draw stack by moving all cards from the discard stack into the draw stack and then
     * shuffling the draw stack after that is the discard stack empty
     * @throws IllegalStateException if the game is not running or if the draw stack is not completely empty
     */
    fun refillDrawStack()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not running"}

        check (game.drawStack.isEmpty()) {"the draw stack is not empty"}

        game.drawStack.addAll(game.discardStack)
        game.drawStack.shuffle()
        game.discardStack.clear()
    }


    /**
     * ends the current game calculate the winner based on the hand ranks
     * @throws IllegalStateException if the game is not running
     */
    fun endGame ()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not running"}
        val playerHandRanks: MutableList<HandRank> = mutableListOf()

        for (player in game.players)
        {
            val allHandCards = player.openHand + player.secretHand

            val playerRank: HandRank = evaluateHandCard(allHandCards)

            playerHandRanks.add(playerRank)

        }


        onAllRefreshables { refreshAfterEndGame(playerHandRanks) }
        rootService.currentGame?.discardStack?.clear()
        rootService.currentGame = null
    }


    /**
     * evaluates a list of exactly 5 cards and returns the resulting hand rank
     * @param cards the player's 5 cards
     * @throws IllegalArgumentException if the cards size is not 5
     * @return the highest calculated [HandRank] achieved by the given cards
     */
    private fun evaluateHandCard (cards : List<Card>) : HandRank
    {
        require(cards.size == 5){"cards size must be 5"}

        val valueCounts: Map<CardValue, Int> = cards.groupingBy { it.value }.eachCount()
        val suitCounts: Map<CardSuit, Int> = cards.groupingBy { it.suit }.eachCount()

        val hasFourOfAKind: Boolean = valueCounts.containsValue(4)
        val hasThreeOfAKind: Boolean = valueCounts.containsValue(3)
        val pairNumber = valueCounts.values.count { it == 2 }

        val isFlush: Boolean = suitCounts.size == 1

        val values: List<CardValue> = cards.map { it.value }
        val isStraight: Boolean = isConsecutive(values)


        val isFullHouse: Boolean = hasThreeOfAKind && pairNumber == 1
        val isStraightFlush: Boolean = isStraight && isFlush

        val isRoyalFlush: Boolean = isStraightFlush && values.contains(CardValue.ACE)

        return when {
            isRoyalFlush -> HandRank.ROYAL_FLUSH
            isStraightFlush -> HandRank.STRAIGHT_FLUSH
            hasFourOfAKind -> HandRank.FOUR_OF_A_KIND
            isFullHouse -> HandRank.FULL_HOUSE
            isFlush -> HandRank.FLUSH
            isStraight -> HandRank.STRAIGHT
            hasThreeOfAKind -> HandRank.THREE_OF_A_KIND
            pairNumber == 2 -> HandRank.TWO_PAIR
            pairNumber == 1 -> HandRank.PAIR
            else -> HandRank.HEIGHT_CADR
        }


    }

    /**
     * checks
     *fi a given list of 5 card values forms a continues sequence
     * @param values A list containing exactly 5 [CardValue] enum entries
     */
    private fun isConsecutive (values: List<CardValue>): Boolean
    {
        if (values.distinct().size != 5)
        {
            return false
        }

        val maximum = values.maxOf { it.ordinal }
        val minimum = values.minOf { it.ordinal }

        return (maximum - minimum == 4)
    }

}