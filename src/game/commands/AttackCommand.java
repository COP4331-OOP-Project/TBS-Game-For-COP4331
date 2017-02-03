package src.game.commands;


import src.game.gameboard.GameBoard;

/**
 * Initiate an attack with an actor
 */
public class AttackCommand<T> extends Command {

    private T actor;            // Actor of action
    private int direction;      // Direction of attack

    // Constructor
    public AttackCommand(GameBoard gameBoard, T actor, int direction, int duration) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        this.direction = direction;
        super.duration = duration;
    }

    // Execute attack command on Gameboard function
    public void exec() {
        gameBoard.handleAttackCmd(actor, direction);
    }

}
