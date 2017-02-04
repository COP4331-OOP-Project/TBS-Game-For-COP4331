package game.commands;

import game.gameboard.GameBoard;

/**
 * Initiate defenses with an actor
 */
public class DefendCommand<T> extends Command {

    private T actor;            // Actor to perform defend
    private int direction;      // Direction of defend

    // Constructor
    public DefendCommand(GameBoard gameBoard, T actor, int direction, int duration) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        this.direction = direction;
        super.duration = duration;
    }

    // Execute from Gameboard defense function
    public void exec() {
        gameBoard.handleDefendCmd(actor, direction);
    }

}
