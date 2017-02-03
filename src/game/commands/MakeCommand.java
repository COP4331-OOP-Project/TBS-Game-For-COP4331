package src.game.commands;


import src.game.gameboard.GameBoard;

/**
 * Initiate making an entity with an actor
 */
public class MakeCommand<T> extends Command {

    private T actor;                    // Actor to create an entity
    private int direction;              // Direction to create in
    private String entityCode;          // Unique entity code to create

    // Constructor
    public MakeCommand(GameBoard gameBoard, T actor, int direction, int duration, String entityCode) {
        super.gameBoard = gameBoard;
        this.actor = actor;
        this.direction = direction;
        super.duration = duration;
        this.entityCode = entityCode;
    }

    // Constructor w/o direction, defaults entity build location to 0
    MakeCommand(T actor, int duration, String entityCode) {
        this.actor = actor;
        this.direction = 0;
        super.duration = duration;
        this.entityCode = entityCode;
    }

    // Execute make from Gameboard function
    public void exec() {
        gameBoard.handleMakeCmd(actor, direction, entityCode);
    }

}
