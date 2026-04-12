package gui

import service.HandRank
import service.Refreshable
import tools.aqua.bgw.core.BoardGameApplication
import service.RootService

/**
 * Represents the main application for the shift poker board game.
 * The application initializes the [RootService] and displays the scenes.
 */
class ShiftPokerApplication : BoardGameApplication("ShiftPoker Game"), Refreshable {

    /**
     * The root service instance. This is used to call service methods and access the entity layer.
     */
    val rootService: RootService = RootService()

    /**
     * The main game scene displayed in the application.
     */
    private val shiftPokerGameScene = ShiftPokerGameScene(rootService)
    private val newGameMenuScene = NewGameMenuScene(rootService)
    private val logMenuScene = LogMenuScene(rootService)
    private val endGameScene = EndGameScene(rootService)
    private val nextPlayerScene = NextPlayerScene(rootService)

    /**
     * Initializes the application
     */
    init {
        rootService.addRefreshable(this)
        rootService.addRefreshable(shiftPokerGameScene)
        rootService.addRefreshable(newGameMenuScene)
        rootService.addRefreshable(logMenuScene)
        rootService.addRefreshable(endGameScene)
        rootService.addRefreshable(nextPlayerScene)

        showMenuScene(newGameMenuScene)
        nextPlayerScene.showMyCardsButton.onMouseClicked = {
            hideMenuScene()
            showGameScene(shiftPokerGameScene)
        }

        shiftPokerGameScene.showLogButton.onMouseClicked = {
            logMenuScene.update()
            showMenuScene(logMenuScene)
        }

        newGameMenuScene.exitButton.onMouseClicked = {
            exit()
        }

        logMenuScene.closeButton.onMouseClicked = {
            hideMenuScene()
        }

        endGameScene.exitButton.onMouseClicked = {
            exit()
        }

        endGameScene.restartButton.onMouseClicked = {
            hideMenuScene()
            showMenuScene(newGameMenuScene)
        }
    }



    override fun refreshAfterStartGame() {
        hideMenuScene()
        showMenuScene(nextPlayerScene)
    }

    override fun refreshAfterNextPlayer() {
        showMenuScene(nextPlayerScene)
    }

    override fun refreshAfterEndGame(handRanks: List<HandRank>) {
        hideMenuScene()
        showMenuScene(endGameScene)
    }
}

