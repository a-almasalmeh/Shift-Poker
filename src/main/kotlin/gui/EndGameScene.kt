package gui

import service.HandRank
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
 * menu displayed at the end of the game
 * Shows the final leaderboard and offers options to restart or exit the game
 */
class EndGameScene(val rootService: RootService): MenuScene(), Refreshable {

    /**
     * label for the header
     */
    val topLabel = Label(
        posX = 200,
        posY = 50,
        width = 1500,
        height = 200,
        text = "",
        font = Font(
            size = 100,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0x919191)
        )
    )

    /**
     * label displaying the result for the first player
     */
    val firstPlayerLabel = Label(
        posX = 200,
        posY = 250,
        width = 1500,
        height = 150,
        text = "",
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
            color = Color(0x919191)
        )
    )

    /**
     * label displaying the result for the second player
     */
    val secondPlayerLabel = Label(
        posX = 200,
        posY = 400,
        width = 1500,
        height = 150,
        text = "",
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
            color = Color(0x919191)
        )
    )

    /**
     * label displaying the result for the third player
     */
    val thirdPlayerLabel = Label(
        posX = 200,
        posY = 550,
        width = 1500,
        height = 150,
        text = "",
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
            color = Color(0x919191)
        )
    )

    /**
     * label displaying the result for the fourth player
     */
    val fourthPlayerLabel = Label(
        posX = 200,
        posY = 700,
        width = 1500,
        height = 150,
        text = "",
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
            color = Color(0x919191)
        )
    )


    /**
     * Button to restart the game
     */
    val restartButton = Button(
        posX = 400,
        posY = 900,
        width = 350,
        height = 80,
        text = "Restart",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xF2FBD2)
        )
    )

    /**
     * Button to exit the game
     */
    val exitButton = Button(
        posX = 1100,
        posY = 900,
        width = 350,
        height = 80,
        text = "Exit",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.NORMAL,
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
            topLabel,
            firstPlayerLabel,
            secondPlayerLabel,
            thirdPlayerLabel,
            fourthPlayerLabel,
            exitButton,
            restartButton
        )
    }


    override fun refreshAfterEndGame(handRanks: List<HandRank>) {
        val game = rootService.currentGame
        if (game != null) {

            topLabel.text = "Player  " + "Hand Rank  "

            /**
             * list of the player names as a string
             */
            val playerNames = game.players.map { it.name }

            /**
             * contains the labels to show on the screen
             */
            val resultLabels = listOf(firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel, fourthPlayerLabel)

            when(game.players.size){

                2 -> {
                    val nameRankMap = mapOf(
                        playerNames[0] to handRanks[0],
                        playerNames[1] to handRanks[1],

                        )
                    val sortedMap = nameRankMap.toList().sortedByDescending { it.second.ordinal }.toMap()


                    for (i in 0..1) {
                        resultLabels[i].text = "${playerNames[i]}   ${sortedMap[playerNames[i]]}"
                    }
                }




                3 -> {
                    val nameRankMap = mapOf(
                        playerNames[0] to handRanks[0],
                        playerNames[1] to handRanks[1],
                        playerNames[2] to handRanks[2],

                        )
                    val sortedMap = nameRankMap.toList().sortedByDescending { it.second.ordinal }.toMap()


                    for (i in 0..2) {
                        resultLabels[i].text = "${playerNames[i]}   ${sortedMap[playerNames[i]]}"
                    }
                }



                4 -> {
                    val nameRankMap = mapOf(
                        playerNames[0] to handRanks[0],
                        playerNames[1] to handRanks[1],
                        playerNames[2] to handRanks[2],
                        playerNames[3] to handRanks[3],

                        )
                    val sortedMap = nameRankMap.toList().sortedByDescending { it.second.ordinal }.toMap()


                    for (i in 0..3) {
                        resultLabels[i].text = "${playerNames[i]}   ${sortedMap[playerNames[i]]}"
                    }
                }
            }
        }


    }
}