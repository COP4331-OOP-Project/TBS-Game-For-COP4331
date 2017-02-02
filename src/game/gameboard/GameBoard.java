package game.gameboard;

import java.util.ArrayList;
import game.Player;
import game.entities.Army;
import game.entities.units.Unit;

/**
 * Gameboard class containing 2D Tile array for board and interaction handler functions
 * */
public class GameBoard {

    private ArrayList<ArrayList<Tile>> GameMap;     // Map for game tiles
    private ArrayList<Player> players;              // Players for game

    // Constructor
    GameBoard(ArrayList<Player> players) {
        this.players = players;                     // Set players
        setupMap();                                 // Setup GameMap
    }

    // Setup the GameMap array with valid Tiles
    private void setupMap() {}

    // Handle decommission command
    public <T> void handleDecommissionCmd(T actor) {}

    // Handle power up command
    public <T> void handlePowerUpCmd(T actor) {}

    // Handle power down command
    public <T> void handlePowerDownCmd(T actor) {}

    // Handle attack command
    public <T> void handleAttackCmd(T actor, int direction) {}

    // Handle defend command
    public <T> void handleDefendCmd(T actor, int direction) {}

    // Handle move command
    public <T> void handleMoveCmd(T actor, int direction) {}

    // Handle heal command
    public <T> void handleHealCmd(T actor, int direction) {}

    // Handle disband army command
    public void handleDisbandArmyCmd(Army actor) {}

    // Handle band army command
    public void handleBandArmyCmd(ArrayList<Unit> actors) {}

}
