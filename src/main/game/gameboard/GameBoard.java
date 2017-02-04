package game.gameboard;

import game.Player;
import game.entities.Army;
import game.entities.factories.EntityFactory;
import game.entities.units.Unit;
import game.entities.RallyPoint;
import game.entities.structures.Structure;


import java.util.ArrayList;
/**
 * Gameboard class containing 2D Tile array for board and interaction handler functions
 * */
public class GameBoard {

    public Tile[][] gameMap;     // Map for game tiles
                                 // 0 is grass, 1 is sand, 2 is water
    private ArrayList<Player> players;              // Players for game

    // Constructor
    //Test constructorc

    public GameBoard(int players) {
        //this.players = players;                     // Set players
        setupMap();                                 // Setup gameMap
    }

    public GameBoard(ArrayList<Player> players) {
        this.players = players;                     // Set players
        setupMap();                                 // Setup gameMap
    }

    // Setup the gameMap array with valid Tiles
    private void setupMap() {
        /*
        int[][] map = new int[][] {
                {0,1,1,2,1},
                {1,1,1,2,0},
                {1,0,0,1,1},
                {1,1,1,1,1},
                {2,1,1,2,2}
        };
        */

        int [][] map = new int [][]{
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };


        gameMap = new Tile[5][];
      for (int i = 0; i<5;i++)
        {
            gameMap[i] = new Tile[5];
            for (int j = 0; j<5;j++)
            {
                Location l = new Location(i,j);
                if(map[i][j] == 0)
                    gameMap[i][j] = new Tile(0,l);
                if(map[i][j] == 1)
                    gameMap[i][j] = new Tile(1,l);
                if(map[i][j] == 2)
                    gameMap[i][j] = new Tile(2,l);
            }
        }
    }


    // Get specific tile with actor location
    private Tile getTileWithLocation(Location l) {
        return gameMap[l.xIndex][l.yIndex];
    }

    //Get the adjecent tile
    private Tile getAdjacentTile(Tile actorTile,int direction) {
        switch(direction) {
            case 0: //Move north
                return gameMap[actorTile.getlocation().xIndex][actorTile.getlocation().yIndex - 1];
            case 45: //Move north-east
                return gameMap[actorTile.getlocation().xIndex+1][actorTile.getlocation().yIndex - 1];
            case 90: //move east
                return gameMap[actorTile.getlocation().xIndex + 1][actorTile.getlocation().yIndex];
            case 135: //move south east
                return gameMap[actorTile.getlocation().xIndex+1][actorTile.getlocation().yIndex + 1];
            case 180: //move south
                return gameMap[actorTile.getlocation().xIndex][actorTile.getlocation().yIndex + 1];
            case 225: //move south west
                return gameMap[actorTile.getlocation().xIndex-1][actorTile.getlocation().yIndex + 1];
            case 270: //move west
                return gameMap[actorTile.getlocation().xIndex - 1][actorTile.getlocation().yIndex];
            case 315: //move north west
                return gameMap[actorTile.getlocation().xIndex-1][actorTile.getlocation().yIndex - 1];
            case 360: //move north
                return gameMap[actorTile.getlocation().xIndex][actorTile.getlocation().yIndex - 1];
            default:
                return null;
        }
    }

    // Get entity with given player's entity id
    private <T> T getEntityWithID() {
        return null;
    }

    // Get tile of given actor
    private <T> Tile getTileOfEntity(T entity) {
        return null;
    }

    private Tile getAdjacentTileWithDirection(Tile tile, int direction) {
        return null;
    }

    // Handle decommission command
    public <T> void handleDecommissionCmd(T actor) {

        // players[actor.playerId].removeEntity(actor.id);        Remove From Player

        // Tile actorTile = getTileWithLocation(actor.location);  Remove from Tile
        // actorTile.removeEntity(actor.id);


    }

    // Handle power up command
    public <T> void handlePowerUpCmd(T actor) {
//        actor.powerUp();
    }

    // Handle power down command
    public <T> void handlePowerDownCmd(T actor) {
//        actor.powerDown();
    }

    // Handle cancel queue command
    public <T> void handleCancelQueueCmd(T actor) {
//        actor.cancelCommandQueue();
    }

    // Handle attack command
    public <T> void handleAttackCmd(T actor, int direction) {

        // Tile actorTile = getTileWithLocation(actor.getLocation());
        // Tile targetTile = getAdjacentTile(actorTile, direction);
        // targetTile.dealDamage(actor.getCombatPower());
        // if (actor.isCommandQueueEmpty()) { Build new command of attack for same area && place it actor queue }

    }

    // Handle defend command
    public <T> void handleDefendCmd(T actor, int direction) {

        // Tile actorTile = getTileWithLocation(actor.getLocation());
        // actorTile must be aware of actor defending direction
        // if (actor.isCommandQueueEmpty()) { Build new command of defend for same area && place it actor queue }

    }

    // Handle move command
    public <T> void handleMoveCmd(Object actor, int direction) {

        // Tile actorTile = getTileWithLoction(actor.getLocation());
        // Tile targetTile = getAdjacentTile(actorTile, direction);

        boolean isArmy = actor instanceof Army;
        boolean isUnit = actor instanceof Unit;
        boolean isStructure = actor instanceof Structure;

        if(isArmy)
        {
            Tile actorTile = getTileWithLocation(((Army)actor).getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            if(targetTile.hasEnemyUnit(((Army)actor).getOwnerID()))
            {
                //(Army)actor.nextCommand();
            }
            else
            {
                actorTile.removeArmy(((Army) actor).getArmyID());
                targetTile.addArmy((Army)actor);
            }
        }
        else if(isUnit)
        {
            Tile actorTile = getTileWithLocation(((Unit)actor).getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            if(targetTile.hasEnemyUnit(((Unit) actor).getOwnerID()))
            {
                ((Unit) actor).nextCommand();
            }
            else {
                actorTile.removeUnit(((Unit) actor).getUnitID());
                targetTile.addUnit((Unit) actor);
            }
        }




        // Check if we can move to the spot without enemy units on it
        // if !(targetTile.hasEnemyUnits(actor.getPlayerId()) {
        //      actorTile.removeEntity(actor.getId());
        //      targetTile.setEntity(actor.getId());
        // }
        // else { // Enemy has the tile, cannot move there, do next command instead
        //      actor.nextCommand();
        // }

    }

    // Handle heal command
    public <T> void handleHealCmd(T actor, int direction) {

        // Tile actorTile = getTileWithLocation(actor.getLocation());

        // Tile targetTile = getAdjacentTile(actorTile, direction);
        // targetTile.heal(actor.getHealValue());

    }

    // Handle make command w/ direction
    public <T> void handleMakeCmd(T actor, int direction, String entityCode) {}

    // Handle disband army command
    public void handleDisbandArmyCmd(Army actor) {
//        actor.disband();
    }

    // Handle band army command
    public void handleBandArmyCmd(ArrayList<Unit> actors) {

//    	Location location = actors.get(0).location.getlocation();
//    	RallyPoint rp = new RallyPoint(location, this);
//    	Army newArmy = EntityFactory.getArmy(location, actors.get(0).getOwner(), rp, actors);
//    	players.get(actors.get(0).getOwner()).addArmy(newArmy);

    }

}
