package gui

import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual


/**
 * a menu scene. Its hides the game board between turns so players cannot see each other's secret cards
 */
class NextPlayerScene(val rootService: RootService): MenuScene(), Refreshable {

    /**
     * title label
     */
    val nextPlayerLabel = Label(
        posX = 530,
        posY = 100,
        width = 800,
        height = 200,
        text = "Next Player",
        font = Font(
            size = 90,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.BLACK,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )

    /**
     * label displaying the name of the player whose turn is next
     */
    val playerNameLabel = Label(
        posX = 530,
        posY = 400,
        width = 800,
        height = 200,
        text = "player name",
        font = Font(
            size = 90,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.BLACK,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )

    /**
     * button for the next player to click once they are ready to see their cards
     */
    val showMyCardsButton = Button(
        posX = 530,
        posY = 850,
        width = 800,
        height = 200,
        text = "Show my cards",
        font = Font(
            size = 70,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.BLACK,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xF2FBD2)
        )
    )

    init {
        addComponents(
            nextPlayerLabel,
            playerNameLabel,
            showMyCardsButton
        )

        background = ColorVisual(Color.LIGHT_GRAY)
    }


    override fun refreshAfterNextPlayer() {
        updateName()
    }

    override fun refreshAfterStartGame() {
       updateName()
    }

    /**
     * helper method to update the [playerNameLabel] with the next player name
     */
    private fun updateName (){
        val game = rootService.currentGame

        if (game != null) {
            val nextPlayer = game.players[game.currentPlayer]
            playerNameLabel.text = nextPlayer.name
        }
    }
}