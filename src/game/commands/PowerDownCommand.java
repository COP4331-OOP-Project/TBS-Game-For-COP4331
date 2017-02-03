package src.game.commands;


import src.game.gameboard.GameBoard;

/**
 * Initiate a change to powered down state with an actor
 */
public class PowerDownCommand<T> extends Command {

    private T actor;                // Actor to power down

    public PowerDownCommand(GameBoard gameBoard, T actor) {
        super.gameBoard = gameBoard;    // Set game board
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute power down from Gameboard function
    public void exec() {
        gameBoard.handlePowerDownCmd(actor);
    }

}
