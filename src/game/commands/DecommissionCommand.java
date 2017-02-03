package src.game.commands;


import src.game.gameboard.GameBoard;

/**
 * Decommission (destroy) the actor
 */
public class DecommissionCommand<T> extends Command {

    private T actor;                    // Actor to decomission

    public DecommissionCommand(GameBoard gameBoard, T actor) {
        super.gameBoard = gameBoard;    // Set game board
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute decommission command in Gameboard function
    public void exec() {
        gameBoard.handleDecommissionCmd(actor);
    }

}
