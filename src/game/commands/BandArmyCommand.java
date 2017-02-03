package src.game.commands;


import src.game.entities.units.Unit;
import src.game.gameboard.GameBoard;

import java.util.ArrayList;

/**
 * Band an established collection of unit actors
 */
public class BandArmyCommand extends Command {

    private ArrayList<Unit> actors;                 // Actor units to band together
    private int direction;                          // Direction of banding

    // Constructor
    public BandArmyCommand(GameBoard gameboard, ArrayList<Unit> actors, int direction, int duration) {
        super.gameBoard = gameboard;
        this.actors = actors;
        this.direction = direction;
        super.duration = duration;
    }

    // Execute command from Gameboard function
    public void exec() {
        gameBoard.handleBandArmyCmd(actors);
    }

}
