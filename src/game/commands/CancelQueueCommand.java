package game.commands;

import game.gameboard.GameBoard;

/**
 * Clear the queued commands of the actor
 */
public class CancelQueueCommand<T> extends Command {

    private T actor;

    CancelQueueCommand(GameBoard gameBoard, T actor) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        super.duration = 0;
    }

    // Execute command from Gameboard function
    public void exec() {
       gameBoard.handleCancelQueueCmd(actor);
    }

}
