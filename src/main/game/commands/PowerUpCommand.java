package game.commands;

import game.gameboard.GameBoard;

/**
 * Initiate a change to powered up state with an actor
 */
public class PowerUpCommand<T> extends Command {

    private T actor;                // Actor to power up

    // Constructor
    public PowerUpCommand(GameBoard gameBoard, T actor) {
        super.gameBoard = gameBoard;    // Set game board
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute power up from Gameboard function
    public void exec() {
        gameBoard.handlePowerUpCmd(actor);
    }

}
