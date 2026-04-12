package service

import entity.Card
import entity.Player


/**
 *
 * service responsible for handling all actions a player can take during their turn
 */
class PlayerService(val rootService: RootService): AbstractRefreshingService()
{
    /**
     * swaps all three Cards from the current player's open hand with all three center cards
     * @throws IllegalStateException if the game is not active or the player has no actions left
     */
    fun swapThree ()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not started"}
        check(game.actionCount in 0..1){"the player has no actions left"}
        check(game.roundCount <= game.maxRounds)


        val swapCards: List<Card> = game.centerCards.toList()

        val currentPlayer = game.players[game.currentPlayer]

        game.centerCards.clear()
        game.centerCards.addAll(currentPlayer.openHand)

        currentPlayer.openHand.clear()
        currentPlayer.openHand.addAll(swapCards)


        game.actionCount++
        game.log.add("$currentPlayer.name swaped all three cards")


        onAllRefreshables { refreshAfterSwapThree() }

        when {
            game.currentPlayer == game.players.size-1 && game.actionCount == 2 &&
                    game.roundCount == game.maxRounds -> rootService.gameService.endGame()

            game.actionCount == 2 -> rootService.gameService.nextPlayer()

        }

    }

    /**
     * swaps exactly one card from the current player's open hand with one card from the center
     *
     * @param handIndex the index of the card in the player's openHand
     * @param centerIndex the index of the card in the center
     * @throws IllegalArgumentException if the provided index out of bound
     * @throws IllegalStateException if the Game is not started or the player has no actions left
     */
    fun swapOne(handIndex: Int, centerIndex: Int)
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not started"}
        require(handIndex in 0..2){"handIndex must be between 0 and 2"}
        require(centerIndex in 0..2){"centerIndex must be between 0 and 2"}
        check(game.actionCount in 0..1){"the player has no actions left"}
        check(game.roundCount <= game.maxRounds)


        val currentPlayer: Player = game.players[game.currentPlayer]

        val swapCard: Card = game.centerCards[centerIndex]

        game.centerCards[centerIndex] = currentPlayer.openHand[handIndex]

        currentPlayer.openHand[handIndex] = swapCard

        game.actionCount++
        game.log.add("The player ${currentPlayer.name} swaped one card " +
                "at hand index $handIndex with center index $centerIndex")

        onAllRefreshables { refreshAfterSwapOne(handIndex, centerIndex) }

        when {
            game.currentPlayer == game.players.size-1 && game.actionCount == 2 &&
                    game.roundCount == game.maxRounds -> rootService.gameService.endGame()

            game.actionCount == 2 -> rootService.gameService.nextPlayer()

        }


    }


    /**
     * the current player decided to take no action and passes the turn to the next player
     * @throws IllegalStateException if the game is not started or the player has no actions left
     */
    fun passTurn()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not started"}
        check(game.actionCount in 0..1){"the player has no actions left"}
        check(game.roundCount <= game.maxRounds)


        val currentPlayer = game.players[game.currentPlayer]
        game.log.add("The player ${currentPlayer.name} passed")


        game.actionCount = 2



        when {
            game.currentPlayer == game.players.size-1 &&
                    game.roundCount == game.maxRounds -> rootService.gameService.endGame()

            else -> rootService.gameService.nextPlayer()

        }
    }


    /**
     * shifts the center cards one position to the left
     * the leftmost card goes to the discard stack and anew card is drawn from the draw stack
     * to fill the rightmost position
     * @throws IllegalStateException if the game is not started or the player has no actions left
     *
     */
    fun shiftLeft()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not started"}
        check(game.actionCount in 0..1){"the player has no actions left"}
        check(game.roundCount <= game.maxRounds)

        val leftCard: Card = game.centerCards.removeFirst()
        game.discardStack.add(leftCard)

        if (game.drawStack.isEmpty())
        {
            rootService.gameService.refillDrawStack()
        }
        game.centerCards.add(game.drawStack.removeLast())


        game.actionCount++

        val currentPlayer = game.players[game.currentPlayer]
        game.log.add("the player ${currentPlayer.name} shifted to the left")

        onAllRefreshables { refreshAfterShiftLeft() }

        when {
            game.currentPlayer == game.players.size-1 && game.actionCount == 2 &&
                    game.roundCount == game.maxRounds -> rootService.gameService.endGame()

            game.actionCount == 2 -> rootService.gameService.nextPlayer()

        }
    }


    /**
     * shifts the center cards one position to the right
     * the rightmost card goes to the discard stack and anew card is drawn from the draw stack
     * to fill the leftmost position
     * @throws IllegalStateException if the game is not started or the player has no actions left
     */
    fun shiftRight()
    {
        val game = rootService.currentGame
        checkNotNull(game){"game is not started"}
        check(game.actionCount in 0..1){"the player has no actions left"}
        check(game.roundCount <= game.maxRounds)

        val rightCard: Card = game.centerCards.removeLast()
        game.discardStack.add(rightCard)

        if (game.drawStack.isEmpty())
        {
            rootService.gameService.refillDrawStack()
        }
        game.centerCards.add(0,game.drawStack.removeLast())


        game.actionCount++
        val currentPlayer = game.players[game.currentPlayer]
        game.log.add("the player ${currentPlayer.name} shifted to the right")

        onAllRefreshables { refreshAfterShiftRight() }

        when {
            game.currentPlayer == game.players.size-1 && game.actionCount == 2 &&
                    game.roundCount == game.maxRounds -> rootService.gameService.endGame()

            game.actionCount == 2 -> rootService.gameService.nextPlayer()

        }
    }


}