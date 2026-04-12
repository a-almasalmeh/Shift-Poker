package gui

import entity.Player
import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.container.Area
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.gamecomponentviews.GameComponentView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.CompoundVisual

/**
 * the main game board scene for Shift Poker
 */
class ShiftPokerGameScene(val rootService: RootService): BoardGameScene(), Refreshable {

    /**
     * helper object to load the card image
     */
    private val imageLoader = CardImageLoader()

    /**
     * stores the index of the currently selected cards from the player's hand
     */
    private var selectedHandIndex: Int = -1

    /**
     * stores the index of the currently selected cards from the center
     */
    private var selectedCenterIndex: Int = -1


    /**
     * button to open the game log menu
     */
    val showLogButton = Button(
        posX = 10,
        posY = 20,
        width = 300,
        height = 60,
        text = "Show Game Log",
        font = Font(
            size = 30,
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

    /**
     * label to display the current round number
     */
    val roundLabel = Label(
        posX = 1550,
        posY = 20,
        width = 300,
        height = 60,
        text = "Round 3 / 5",
        font = Font(
            size = 30,
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
     * the gary background area
     */
    val area = Area<GameComponentView>(
        posX = 250,
        posY = 120,
        width = 1500,
        height = 800,
        visual = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )

    //region center
    val drawStackView = CardView(
        posX = 650,
        posY = 460,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    )

    // center cards
    val centerCard1 = CardView(
        posX = 800,
        posY = 460,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedCenterIndex = 0
        }
    }
    val centerCard2 = CardView(
        posX = 950,
        posY = 460,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedCenterIndex = 1
        }
    }
    val centerCard3 = CardView(
        posX = 1100,
        posY = 460,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedCenterIndex = 2
        }
    }


    val discardStackView = CardView(
        posX = 1250,
        posY = 460,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    )
    //endregion

    // region bottom
    val bottomOpenCard1 = CardView(
        posX = 870,
        posY = 670,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedHandIndex = 0
        }
    }
    val bottomOpenCard2 = CardView(
        posX = 950,
        posY = 670,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedHandIndex = 1
        }
    }
    val bottomOpenCard3 = CardView(
        posX = 1030,
        posY = 670,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    ).apply {
        onMouseClicked =  {
            selectedHandIndex = 2
        }
    }
    // bottom secret cards
    val bottomSecretCard1 = CardView(
        posX = 900,
        posY = 800,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    )
    val bottomSecretCard2 = CardView(
        posX = 1000,
        posY = 800,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0xB4B4B4)
        ),
        back = ColorVisual(
            color = Color(0xB4B4B4)
        )
    )
    // endregion



    // region left
    val openCardLeft1 = CardView(
        posX = 450,
        posY = 400,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }

    val openCardLeft2 = CardView(
        posX = 450,
        posY = 480,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    val openCardLeft3 = CardView(
        posX = 450,
        posY = 560,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }



    val secretCardLeft1 = CardView(
        posX = 300,
        posY = 440,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply{
        rotation = 90.0
    }
    val secretCardLeft2 = CardView(
        posX = 300,
        posY = 540,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    // endregion

    // region right
    val rightOpenHand0 = CardView(
        posX = 1450,
        posY = 560,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    val rightOpenHand1 = CardView(
        posX = 1450,
        posY = 480,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    val rightOpenHand2 = CardView(
        posX = 1450,
        posY = 400,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    // right secret cards
    val rightSecretCard0 = CardView(
        posX = 1570,
        posY = 540,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    val rightSecretCard1 = CardView(
        posX = 1570,
        posY = 440,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    ).apply {
        rotation = 90.0
    }
    //endregion

    // region top
    val topOpenCard1 = CardView(
        posX = 1030,
        posY = 300,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )

    val topOpenCard2 = CardView(
        posX = 950,
        posY = 300,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )

    val topOpenCard3 = CardView(
        posX = 870,
        posY = 300,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )

    // top secret cards
    val topSecretCard1 = CardView(
        posX = 1000,
        posY = 180,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )
    val topSecretCard2 = CardView(
        posX = 900,
        posY = 180,
        width = 70,
        height = 100,
        front = ColorVisual(
            color = Color(0x8E8E8E)
        ),
        back = ColorVisual(
            color = Color(0x8E8E8E)
        )
    )
    // endregion


    // region player name
    private val bottomName = Label(posX = 660,
        posY = 650,
        width = 300,
        height = 40,
        font = Font(24, fontWeight = Font.FontWeight.BOLD))

    private val topName = Label(posX = 660,
        posY = 100,
        width = 300,
        height = 40,
        font = Font(24, fontWeight = Font.FontWeight.BOLD))

    private val leftName = Label(posX = 100,
        posY = 350,
        width = 300,
        height = 40,
        font = Font(24, fontWeight = Font.FontWeight.BOLD))

    private val rightName = Label(posX = 1300,
        posY = 350,
        width = 300,
        height = 40,
        font = Font(24, fontWeight = Font.FontWeight.BOLD))

    // endregion


    // region Buttons
    /**
     * button to swap one selected card from the hand with one from the center
     */
    val swapOneButton = Button(
        posX = 150,
        posY = 950,
        width = 200,
        height = 70,
        text = "SwapOne",
        font = Font(
            size = 30,
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
    ).apply {
        onMouseClicked = {
            if ((selectedHandIndex > -1) && selectedCenterIndex > -1){
                rootService.playerService.swapOne(selectedHandIndex, selectedCenterIndex)
                selectedHandIndex = -1
                selectedCenterIndex = -1
            }

        }
    }

    /**
     * button to swap all three open hand cards with the three center cards
     */
    val swapThreeButton = Button(
        posX = 500,
        posY = 950,
        width = 200,
        height = 70,
        text = "SwapThree",
        font = Font(
            size = 30,
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
    ).apply {
        onMouseClicked = {
            rootService.playerService.swapThree()
        }
    }

    /**
     * button to skip the current turn
     */
    val passButton = Button(
        posX = 850,
        posY = 950,
        width = 200,
        height = 70,
        text = "Pass",
        font = Font(
            size = 30,
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
    ).apply {
        onMouseClicked = {
            rootService.playerService.passTurn()
        }
    }

    /**
     * button to shift the center cards to the left
     */
    val shiftLeftButton = Button(
        posX = 1200,
        posY = 950,
        width = 200,
        height = 70,
        text = "ShiftLeft",
        font = Font(
            size = 30,
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
    ).apply {
        onMouseClicked = {
            rootService.playerService.shiftLeft()
        }
    }

    /**
     * button to shift the center cards to the right
     */
    val shiftRightButton = Button(
        posX = 1550,
        posY = 950,
        width = 200,
        height = 70,
        text = "ShiftRight",
        font = Font(
            size = 30,
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
    ).apply {
        onMouseClicked = {
            rootService.playerService.shiftRight()
        }
    }
    // endregion



    init {

        addComponents(
            area,
            bottomOpenCard1,
            bottomOpenCard2,
            bottomOpenCard3,
            bottomSecretCard1,
            bottomSecretCard2,
            topOpenCard1,
            topOpenCard2,
            topSecretCard2,
            topOpenCard3,
            topSecretCard1,
            rightOpenHand0,
            rightOpenHand1,
            rightOpenHand2,
            rightSecretCard0,
            rightSecretCard1,
            drawStackView,
            centerCard1,
            centerCard2,
            centerCard3,
            discardStackView,
            showLogButton,
            roundLabel,
            bottomName,
            topName,
            leftName,
            rightName,
            secretCardLeft1,
            secretCardLeft2,
            openCardLeft1,
            openCardLeft2,
            openCardLeft3,
            swapOneButton,
            swapThreeButton,
            passButton,
            shiftLeftButton,
            shiftRightButton,
        )

        background = ColorVisual(Color.LIGHT_GRAY)
    }

    //  region refresh methods
    override fun refreshAfterStartGame() {
        updateAll()
    }


    override fun refreshAfterNextPlayer() {
        updateAll()
    }


    override fun refreshAfterSwapOne(handIndex: Int, centerIndex: Int) {
        updateCurrentPlayerCards()
        updateCenterCards()
    }


    override fun refreshAfterSwapThree() {
        updateCurrentPlayerCards()
        updateCenterCards()
    }


    override fun refreshAfterShiftLeft() {
        updateCenterCards()
        updateStacks()
    }



    override fun refreshAfterShiftRight() {
        updateCenterCards()
        updateStacks()
    }
    // endregion


    // region helper Functions
    private fun updateCenterCards(){
        val game = rootService.currentGame
        if (game != null) {
            val centerView = listOf(centerCard1, centerCard2, centerCard3)
            roundLabel.text = "Round ${game.roundCount} / ${game.maxRounds}"
            for (i in 0..2){
                val card = game.centerCards[i]
                centerView[i].frontVisual = imageLoader.frontImageFor(card.suit, card.value)
                centerView[i].backVisual = imageLoader.backImage
                centerView[i].showFront()
            }
        }
    }


    private fun updateStacks (){
        val game = rootService.currentGame
        if (game != null) {
            roundLabel.text = "Round ${game.roundCount} / ${game.maxRounds}"
            if (game.drawStack.isNotEmpty()){
                drawStackView.backVisual = imageLoader.backImage
                drawStackView.showBack()
            }


            if (game.discardStack.isNotEmpty()){
                val card = game.discardStack.last()
                discardStackView.frontVisual = imageLoader.frontImageFor(card.suit, card.value)
                discardStackView.backVisual = imageLoader.backImage
                discardStackView.showFront()
                discardStackView.isVisible = true
            }else{
                discardStackView.isVisible = false
            }
        }
    }


    private fun updateCurrentPlayerCards(){
        val game = rootService.currentGame
        if (game != null) {
            val activePlayer = game.players[game.currentPlayer]
            bottomName.text = activePlayer.name
            roundLabel.text = "Round ${game.roundCount} / ${game.maxRounds}"
            val activePlayerOpenHCardsView  = listOf(bottomOpenCard1, bottomOpenCard2, bottomOpenCard3)
            val activePlayerSecretCardsView = listOf(bottomSecretCard1, bottomSecretCard2)

            drawPlayerCards(activePlayer, activePlayerOpenHCardsView,
                activePlayerSecretCardsView, true)
        }
    }

    /**
     * reads the current game state from and assigns the correct card images
     *
     */
    private fun updateAll() {
        val game = rootService.currentGame?: return
        roundLabel.text = "Round ${game.roundCount} / ${game.maxRounds}"

        updateCenterCards()
        updateStacks()
        updateCurrentPlayerCards()
        //hideAllSecretCards()

        // draw components based on player count
        when(val numPlayers = game.players.size) {

            2 -> {
                val topPlayer = game.players[(game.currentPlayer+1) % numPlayers]
                topName.text = topPlayer.name

                drawPlayerCards(topPlayer, listOf(topOpenCard1, topOpenCard2, topOpenCard3),
                    listOf(topSecretCard1, topSecretCard2), false)
            }



            3 -> {
                val rightPlayer= game.players[(game.currentPlayer+1) % numPlayers]
                rightName.text = rightPlayer.name

                drawPlayerCards(rightPlayer, listOf(rightOpenHand0, rightOpenHand1, rightOpenHand2),
                    listOf(rightSecretCard0, rightSecretCard1), false)



                val leftPlayer= game.players[(game.currentPlayer+2) % numPlayers]
                leftName.text = leftPlayer.name

                drawPlayerCards(leftPlayer, listOf( openCardLeft1,openCardLeft2, openCardLeft3),
                    listOf(secretCardLeft1, secretCardLeft2), false)

            }

            4 -> {
                val topPlayer = game.players[(game.currentPlayer+2) % numPlayers]
                topName.text = topPlayer.name

                drawPlayerCards(topPlayer, listOf(topOpenCard1, topOpenCard2, topOpenCard3),
                    listOf(topSecretCard1, topSecretCard2), false)

                val rightPlayer= game.players[(game.currentPlayer+3) % numPlayers]
                rightName.text = rightPlayer.name

                drawPlayerCards(rightPlayer, listOf(rightOpenHand0, rightOpenHand1, rightOpenHand2),
                    listOf(rightSecretCard0, rightSecretCard1), false)



                val leftPlayer= game.players[(game.currentPlayer+1) % numPlayers]
                leftName.text = leftPlayer.name

                drawPlayerCards(leftPlayer, listOf(openCardLeft1, openCardLeft2, openCardLeft3),
                    listOf(secretCardLeft1, secretCardLeft2), false)
            }
        }


    }



    /**
     * assigns the correct image to a player's cards
     * @param player the player whose cards should be drawn
     * @param openCardsView the CardViews for the open cards
     * @param secretCardsView the CardViews for the secret cards
     * @param showSecretCards true if the secret cards should be revealed
     */
    private fun drawPlayerCards(player: Player, openCardsView: List<CardView>,
                                secretCardsView: List<CardView>, showSecretCards: Boolean) {

        for (i in 0..2) {
            val card = player.openHand[i]
            openCardsView[i].frontVisual = imageLoader.frontImageFor(card.suit, card.value)
            openCardsView[i].backVisual = imageLoader.backImage
            openCardsView[i].showFront()
        }

        for (i in 0..1) {
            val card = player.secretHand[i]
            secretCardsView[i].frontVisual = imageLoader.frontImageFor(card.suit, card.value)
            secretCardsView[i].backVisual = imageLoader.backImage

            if (showSecretCards)
            {
                secretCardsView[i].showFront()
            }else{
                secretCardsView[i].showBack()
            }
        }


    }

    // endregion
}