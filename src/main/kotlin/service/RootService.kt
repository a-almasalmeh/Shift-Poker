package service

import entity.ShiftPoker


/**
 * The root service class is responsible for managing services and the entity layer reference.
 * This class acts as a central hub for every other service within the application.
 *
 */
class RootService {

    var currentGame: ShiftPoker? = null

    val gameService: GameService = GameService(this)
    val playerService: PlayerService = PlayerService(this)

    /**
     * Adds a new [Refreshable] to the list of refreshables.
     * @param newRefreshable The [Refreshable] to be added
     */
    fun addRefreshable (newRefreshable: Refreshable)
    {
        gameService.addRefreshable(newRefreshable)
        playerService.addRefreshable(newRefreshable)
    }

}