package game.commands;

import game.gameboard.GameBoard;

/**
 * Initiate a move with an actor
 */
public class MoveCommand<T> extends Command {

    private T actor;            // Actor to carry out move
    private int direction;      // Direction of move

    // Constructor
    MoveCommand(GameBoard gameBoard, T actor, int direction, int duration) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        this.direction = direction;
        super.duration = duration;
    }

    // Execute move from Gameboard function
    public void exec() {
        gameBoard.handleMoveCmd(actor, direction);
    }

}
