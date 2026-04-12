package gui

import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextArea
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.CompoundVisual

/**
 * a menu scene that displays the game log
 */
class LogMenuScene(val rootService: RootService): MenuScene(), Refreshable {

    /**
     * title label
     */
    val label = Label(
        posX = 150,
        posY = 200,
        width = 1600,
        height = 150,
        text = "Log screen",
        font = Font(
            size = 100,
            color = Color(0x000000),
            family = "Chalkboard SE",
            fontWeight = Font.FontWeight.BLACK,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = CompoundVisual()
    )

    /**
     * text area that contains the actual log text
     */
    val textArea = TextArea(
        posX = 150,
        posY = 350,
        width = 1600,
        height = 500,
        text = "",
        prompt = "",
        font = Font(
            size = 30,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xB4B4B4)
        )
    )

    /**
     * button to close the log screen and return to the game board
     */
    val closeButton = Button(
        posX = 750,
        posY = 950,
        width = 400,
        height = 100,
        text = "Close",
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
        addComponents(label, textArea, closeButton)
    }

    /**
     * clears the current text area and loads the log entries
     */
    fun update ()
    {
      textArea.text = ""
      val game = rootService.currentGame
      if (game != null) {
          for (logInformation in game.log)
          {
              textArea.text += ". $logInformation\n"
          }
      }
    }



}