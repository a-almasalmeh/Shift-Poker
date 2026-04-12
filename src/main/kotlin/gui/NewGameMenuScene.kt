package gui

import service.HandRank
import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

/**
 * menu scene displayed before a game starts.
 * allows user to enter player names, select the number of rounds and start the game
 */
class NewGameMenuScene(val rootService: RootService): MenuScene(), Refreshable {

    /**
     * input field for the first player's name
     */
    private val player1Input = TextField(
        posX = 60,
        posY = 70,
        width = 600,
        height = 100,
        text = "",
        prompt = "Player name 1",
        font = Font(
            size = 30,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.LIGHT,
            fontStyle = Font.FontStyle.ITALIC
        ),
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )

    /**
     * input field for the second player's name
     */
    private val player2Input = TextField(
        posX = 60,
        posY = 200,
        width = 600,
        height = 100,
        text = "",
        prompt = "Player name 2",
        font = Font(
            size = 30,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.LIGHT,
            fontStyle = Font.FontStyle.ITALIC
        ),
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )

    /**
     * input field for the hird player's name
     */
    private val player3Input = TextField(
        posX = 60,
        posY = 330,
        width = 600,
        height = 100,
        text = "",
        prompt = "Player name 3",
        font = Font(
            size = 30,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.LIGHT,
            fontStyle = Font.FontStyle.ITALIC
        ),
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )

    /**
     * input field for the fourth player's name
     */
    private val player4Input = TextField(
        posX = 60,
        posY = 460,
        width = 600,
        height = 100,
        text = "",
        prompt = "Player name 4",
        font = Font(
            size = 30,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.LIGHT,
            fontStyle = Font.FontStyle.ITALIC
        ),
        visual = ColorVisual(
            color = Color(0xB5B5B5)
        )
    )


    /**
     * internal counter for the selected number of rounds
     */
    private var rounds = 2

    private val roundsLabel = Label(
        posX = 45,
        posY = 600,
        width = 200,
        height = 100,
        text = "Rounds:",
        font = Font(
            size = 40,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    /**
     * label displaying the currently selected number of rounds
     */
    private val roundsNumber = Label(
        posX = 150,
        posY = 740,
        width = 200,
        height = 60,
        text = "2",
        font = Font(
            size = 40,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xC6C6C6)
        )
    )

    /**
     * button to decrease the number of rounds
     */
    private val minusButton: Button = Button(
        posX = 90,
        posY = 740,
        width = 60,
        height = 60,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xC6C6C6)
        )
    ).apply {
        onMouseClicked = {
            if (rounds > 2)
            {
                rounds--
                roundsNumber.text = "$rounds"
            }
        }
    }

    /**
     * button to increase the number of rounds
     */
    private val plusButton: Button = Button(
        posX = 350,
        posY = 740,
        width = 60,
        height = 60,
        text = "+",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xC6C6C6)
        )
    ).apply {
        onMouseClicked = {
            if (rounds < 7)
            {
                rounds++
                roundsNumber.text = "$rounds"
            }
        }
    }

    /**
     * button to start the game
     */
    val startGameButton = Button(
        posX = 1100,
        posY = 200,
        width = 550,
        height = 150,
        text = "Start Game",
        font = Font(
            size = 50,
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
    ).apply {
        onMouseClicked = {
            val names = listOf(player1Input.text, player2Input.text, player3Input.text, player4Input.text)

            val activePlayers = names.filter { it.isNotBlank() }
            rootService.gameService.startGame(activePlayers, rounds)
        }
    }

    /**
     * button to exit the game
     */
    val exitButton = Button(
        posX = 1180,
        posY = 450,
        width = 400,
        height = 100,
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
            color = Color(0xC6C6C6)
        )
    )




    init {
        addComponents(player1Input,
            player2Input,
            player3Input,
            player4Input,
            roundsLabel,
            roundsNumber,
            minusButton,
            plusButton,
            startGameButton,
            exitButton
        )

        background = ColorVisual(Color.LIGHT_GRAY)
    }

    override fun refreshAfterEndGame(handRanks: List<HandRank>) {
        player1Input.text = ""
        player2Input.text = ""
        player3Input.text = ""
        player4Input.text = ""

        roundsNumber.text = "2"
    }
}