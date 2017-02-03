package game.commands;

import game.gameboard.GameBoard;

/**
 * Initiate a heal with an actor
 */
public class HealCommand<T> extends Command {

    private T actor;            // Actor to perform heal
    private int direction;      // Direction of heal

    // Constructor
    public HealCommand(GameBoard gameBoard, T actor, int direction, int duration) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        this.direction = direction;
        super.duration = duration;
    }

    // Execute heal in Gameboard funcion
    public void exec() {
        gameBoard.handleHealCmd(actor, direction);
    }

}
