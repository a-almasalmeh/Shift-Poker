package service


/**
 * [Refreshable] implementation that refreshes nothing, but remembers
 * if a refresh method has been called (since last [reset])
 */
class TestRefreshable: Refreshable {

    var refreshAfterStartGameCalled: Boolean = false
        private set

    var refreshAfterSwapOneCalled: Boolean = false
        private set

    var refreshAfterSwapThreeCalled: Boolean = false
        private set

    var refreshAfterEndGameCalled: Boolean = false
        private set

    var refreshAfterShiftLeftCalled: Boolean = false
        private set

    var refreshAfterShiftRightCalled: Boolean = false
        private set

    var refreshAfterNextPlayerCalled: Boolean = false
        private set
    /**
     * resets all *Called properties to false
     */
    fun reset() {
        refreshAfterStartGameCalled = false
        refreshAfterSwapOneCalled = false
        refreshAfterSwapThreeCalled = false
        refreshAfterEndGameCalled = false
        refreshAfterShiftLeftCalled = false
        refreshAfterShiftRightCalled = false
        refreshAfterNextPlayerCalled = false

    }

    override fun refreshAfterSwapOne(handIndex: Int, centerIndex: Int)
    {
        refreshAfterSwapOneCalled = true
    }

    override fun refreshAfterSwapThree() {
        refreshAfterSwapThreeCalled = true
    }

    override fun refreshAfterStartGame() {
        refreshAfterStartGameCalled = true
    }

    override fun refreshAfterEndGame(handRanks: List<HandRank>) {
        refreshAfterEndGameCalled = true
    }

    override fun refreshAfterShiftLeft() {
        refreshAfterShiftLeftCalled = true
    }

    override fun refreshAfterShiftRight() {
        refreshAfterShiftRightCalled = true
    }

    override fun refreshAfterNextPlayer() {
        refreshAfterNextPlayerCalled = true
    }
}