package game.commands;


import game.entities.Army;
import game.gameboard.GameBoard;

/**
 * Disband an established actor army
 */
public class DisbandArmyCommand extends Command {

    private Army actor;                 // army actor to disband

    // Constructor
    public DisbandArmyCommand(GameBoard gameBoard, Army actor) {
        super.gameBoard = gameBoard;    // Set game board
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute army disband from Gameboard function
    public void exec() {
        gameBoard.handleDisbandArmyCmd(actor);
    }

}
