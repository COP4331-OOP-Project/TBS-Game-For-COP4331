package game.gameboard;

import controls.ModeController;
import game.Player;
import game.commands.AttackCommand;
import game.entities.Army;
import game.entities.ICommandable;
import game.entities.RallyPoint;
import game.entities.factories.EntityFactory;
import game.entities.factories.UnknownEntityCodeException;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/**
 * Gameboard class containing 2D Tile array for board and interaction handler functions
 * */
public class GameBoard {

    public Tile[][] gameMap;     // Map for game tiles
                                 // 0 is grass, 1 is sand, 2 is water
    private ArrayList<Player> players;              // Players for game
    private static final int BOARD_SIZE = 25;
    private final static Logger log = LogManager.getLogger(ModeController.class);



    // Constructor
/*
    Remove old constructor
    public GameBoard(int players) {
        //this.players = players;                     // Set players
        setupMap();                                 // Setup gameMap
    }
*/
    public GameBoard(ArrayList<Player> players) {
    	
        this.players = players;                     // Set players
        setupMap();                                 // Setup gameMap
    }

    public Player getPlayer(int index) {
        if (index > players.size() - 1) return null;
        return this.players.get(0);
    }

    // Setup the gameMap array wit5h valid Tiles
    private void setupMap() {
        int[][] map = new int[][] {
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2},
                {2,1,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,2,2,2,2,2,2},
                {2,1,1,1,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,0,0,0,2,2,2},
                {2,2,1,1,1,1,1,0,0,0,0,1,1,0,1,0,0,0,0,1,1,2,2,2,2},
                {2,2,2,2,1,1,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0,1,2,2,2},
                {2,2,2,2,1,1,0,0,1,1,0,1,0,1,1,0,0,0,0,0,0,1,2,2,2},
                {2,2,2,1,1,1,0,1,2,1,0,1,1,0,1,0,0,1,0,1,0,2,2,2,2},
                {2,2,1,1,1,0,1,2,2,2,1,1,1,0,0,0,0,0,0,0,1,2,2,2,2},
                {2,1,1,1,1,0,2,2,2,2,2,2,2,2,0,1,0,0,0,0,1,2,2,2,2},
                {2,1,1,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,1,1,2,2,2,2},
                {2,2,2,2,2,2,2,2,1,1,1,1,1,2,2,1,0,0,0,0,1,2,2,2,2},
                {2,2,2,2,2,2,2,2,1,1,0,0,0,2,0,0,0,0,0,1,1,2,2,2,2},
                {2,2,2,2,2,2,1,1,1,1,1,1,1,1,0,0,2,0,0,1,1,2,2,2,2},
                {2,2,2,2,1,1,0,0,1,1,0,0,1,0,1,2,2,0,0,1,1,2,2,2,2},
                {2,2,2,1,1,1,0,0,1,1,0,1,1,0,0,0,2,2,0,1,1,2,2,2,2},
                {2,2,1,1,1,0,0,0,0,0,0,1,0,0,0,1,2,2,0,1,1,2,2,2,2},
                {2,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,2,2,2,2,2,2,2,2,2},
                {2,1,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,2,2,2,2,2,2,2},
                {2,0,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0,1,2,2,2,2,2,2,2},
                {2,1,1,1,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2},
                {2,2,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,2,2,2,2,2},
                {2,1,1,1,1,1,0,0,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
        };


      gameMap = new Tile[BOARD_SIZE][];
      for (int i = 0; i < BOARD_SIZE; i++)
        {
            gameMap[i] = new Tile[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE;j++)
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
        return gameMap[l.getX()][l.getY()];
    }

    //Get the adjecent tile
    private Tile getAdjacentTile(Tile actorTile,int direction) {
        switch(direction) {
            case 0: //Move north
                return gameMap[actorTile.getLocation().getX()][actorTile.getLocation().getY() - 1];
            case 45: //Move north-east
                return gameMap[actorTile.getLocation().getX()+1][actorTile.getLocation().getY() - 1];
            case 90: //move east
                return gameMap[actorTile.getLocation().getX() + 1][actorTile.getLocation().getY()];
            case 135: //move south east
                return gameMap[actorTile.getLocation().getX()+1][actorTile.getLocation().getY() + 1];
            case 180: //move south
                return gameMap[actorTile.getLocation().getX()][actorTile.getLocation().getY() + 1];
            case 225: //move south west
                return gameMap[actorTile.getLocation().getX()-1][actorTile.getLocation().getY() + 1];
            case 270: //move west
                return gameMap[actorTile.getLocation().getX() - 1][actorTile.getLocation().getY()];
            case 315: //move north west
                return gameMap[actorTile.getLocation().getX()-1][actorTile.getLocation().getY() - 1];
            case 360: //move north
                return gameMap[actorTile.getLocation().getX()][actorTile.getLocation().getY() - 1];
            default:
                return null;
        }
    }

    // Get entity with given player's entity id
    private <T> T getEntityWithID() {
       //Returns the entity base on the iD, may not be implemented - Shen
        return null;
    }

    // Get tile of given actor
    private <T> Tile getTileOfEntity(Object entity) {
        boolean isArmy = entity instanceof Army;
        boolean isUnit = entity instanceof Unit;
        boolean isStructure = entity instanceof Structure;

        if(isArmy)
            return gameMap[((Army)entity).getLocation().getX()][((Army)entity).getLocation().getY()];
        else if(isUnit)
            return gameMap[((Unit)entity).getLocation().getX()][((Unit)entity).getLocation().getY()];
        else if(isStructure)
            return gameMap[((Structure)entity).getLocation().getX()][((Structure)entity).getLocation().getY()];

        return null;
    }

    // Handle decommission command
    public <T> void handleDecommissionCmd(T actor) {

        // Find instance type and call remove command on unit
        if(actor instanceof Army){
            Tile actorTile = getTileWithLocation( ( (Army) actor ).getLocation());
            actorTile.removeArmy(( (Army) actor ).getArmyID());
        }
        else if(actor instanceof Unit){
            Tile actorTile = getTileWithLocation( ( (Unit) actor ).getLocation());
            actorTile.removeUnit( ( (Unit) actor ).getUnitID());
        }
        else if(actor instanceof Structure){
            Tile actorTile = getTileWithLocation( ( (Structure) actor ).getLocation());
            actorTile.removeStructure();
        }
    }

    // Handle power up command
    public <T> void handlePowerUpCmd(T actor) {

        // Find instance type and call actor's command
        if(actor instanceof Army){
           // ( (Army) actor ).powerUp();
        }
        else if(actor instanceof Unit){
            ( (Unit) actor ).powerUp();
        }
        else if(actor instanceof Structure){
            ( (Structure) actor ).powerUp();
        }
    }

    // Handle power down command
    public <T> void handlePowerDownCmd(T actor) {

        // Find instance type and call actor's command
        if(actor instanceof Army){
//            ( (Army) actor ).powerDown();
        }
        else if(actor instanceof Unit){
            ( (Unit) actor ).powerDown();
        }
        else if(actor instanceof Structure){
            ( (Structure) actor ).powerDown();
        }
    }

    // Handle cancel queue command
    public <T> void handleCancelQueueCmd(T actor) {

        // Find instance type and call cancel command queue on it
        if(actor instanceof Army){
           // ( (Army) actor ).cancelCommandQueue();
        }
        else if(actor instanceof Unit){
            ( (Unit) actor ).cancelQueuedCommands();
        }
        else if(actor instanceof Structure){
            ( (Structure) actor ).cancelQueuedCommands();
        }

    }

    // Handle attack command
    public <T> void handleAttackCmd(T actor, int direction) {

        // Find instance type and perform attack handle
        if(actor instanceof Army) {

            Army army = (Army) actor;

            Tile actorTile = getTileWithLocation( army.getLocation() );
            Tile targetTile = getAdjacentTile(actorTile, direction);
//            targetTile.attackOccupants();

//            // If queue is empty, pass another attack command for next turn
//            if (army.isQueueEmpty()) {
//                AttackCommand<Army> atkCmd = new AttackCommand<Army>(this, army, direction, 0);
//                army.addCommandToQueue(atkCmd);
//            }

        }
        else if(actor instanceof Structure) {

            Structure struct = (Structure) actor;

            Tile actorTile = getTileWithLocation(struct.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);
            targetTile.attackOccupants(((Structure) actor).getAttackDamage());

            // If queue is empty, pass another attack command for next turn
            if (struct.isQueueEmpty()) {
                AttackCommand<Structure> atkCmd = new AttackCommand<Structure>(this, struct, direction, 0);
                struct.addCommandToQueue(atkCmd);
            }

        }
        else if(actor instanceof Unit) {
            System.out.println("Unit can't attack");
        }

    }

    // Handle defend command
    public <T> void handleDefendCmd(T actor, int direction) {

        // Find instance type and execute defense
        if(actor instanceof Army) {

            Army army = (Army) actor;

            Tile actorTile = getTileWithLocation(army.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            //actorTile.setDefendDirection();
        }
        else if(actor instanceof Structure) {

            Structure struct = (Structure) actor;

            Tile actorTile = getTileWithLocation(struct.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

        }
        else if(actor instanceof Unit) {
            System.out.println("Unit can't defend");
        }
    }

    // Handle move command
    public <T> void handleMoveCmd(Object actor, int direction) {


        // Find instance type and then execute move
        if(actor instanceof Army) {

            Army army = (Army) actor;

            Tile actorTile = getTileWithLocation(army.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            if(targetTile.hasEnemyUnit(army.getOwnerID())) {
                //(Army)actor.nextCommand();
            }
            else if (targetTile.isImpassable()) {
                log.error("Cannot move to tile, it is impassable!");
                // Clear army commands
            }
            else  {
                actorTile.removeArmy(army.getArmyID());
                targetTile.addArmy(army);
            }
        }
        else if(actor instanceof Unit) {

            Unit unit = (Unit) actor;

            Tile actorTile = getTileWithLocation(unit.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            if(!targetTile.hasEnemyUnit(unit.getOwnerID())) {
                unit.nextCommand();
            } else if (targetTile.isImpassable()) {
                log.error("Cannot move to tile, it is impassable!");
                unit.cancelQueuedCommands();
            }
            else {
                actorTile.removeUnit(unit.getUnitID());
                targetTile.addUnit(unit);
            }
        }
        else if (actor instanceof Structure) {
            System.out.println("Structure cannot move");
        }
    }

    // Handle heal command
    public <T> void handleHealCmd(T actor, int direction) {

        // Find instance type and exec heal command
        if(actor instanceof Army) {

            Army army = (Army) actor;   // Cast army

            // Get actor location and target of heal
            Tile actorTile = getTileWithLocation(army.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            // Todo: Find amount to heal by
            targetTile.healOccupants(10);     // Heal occupants of tile

        }
        else if(actor instanceof Unit) {

            Unit unit = (Unit) actor;             // Cast

            // Get actor location and target of heal
            Tile actorTile = getTileWithLocation(unit.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            // Todo: Find amount to heal by
            targetTile.healOccupants(10);         // Heal occupants of tile

        }
        else if (actor instanceof Structure) {

            Structure struct = (Structure) actor;   // Cast

            // Get actor location and target of heal
            Tile actorTile = getTileWithLocation(struct.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

            // Todo: Find amount to heal by
            targetTile.healOccupants(10);             // Heal occupants of tile
        }

    }

    // Handle make command w/ direction
    public <T> void handleMakeCmd(T _actor, int direction, String entityCode) {

        ICommandable actor;         // Actor
        Object newEntity;           // New entity
        Tile actorTile;             // Actor Tile

        // Test for known entity code msg
        try {

            actor = (ICommandable) _actor;                          // Cast actor and create new entity
            newEntity = EntityFactory.getEntity(actor.getLocation(), actor.getOwnerID(), entityCode);

            actorTile = getTileWithLocation(actor.getLocation());   // Get postion to add new unit on

            // Test new entity instance type and add to same tile as actor
            if(newEntity instanceof Unit) {

                Unit unit = (Unit) newEntity;               // Cast as unit

                // Add unit to owner and the tile
                players.get(actor.getOwnerID()).addUnit(unit);
                actorTile.addUnit(unit);

            }
            else if (newEntity instanceof Structure) {

                Structure struct = (Structure) newEntity;   // Cast as struct

                // Add structure to owner and the tile
                players.get(actor.getOwnerID()).addStructure(struct);
                actorTile.setStructure(struct);

            }

        } catch (UnknownEntityCodeException e) {
            log.error(e.getLocalizedMessage()); // Handle unknown entity code error
        }

    }

    // Handle disband army command
    public void handleDisbandArmyCmd(Army actor) {
        //Set All units to stand by
        actor.disbandArmy();
        //Set Player's reference to null
        players.get(actor.getOwnerID()).removeArmy(actor);
        //Set Players reference to rally point to null
        players.get(actor.getOwnerID()).removeRallyPoint(actor.getRp());
        //Set rallypoints reference to null
        actor.getRp().setArmy(null);
        //Set Tile reference to null
        gameMap[actor.getLocation().getX()][actor.getLocation().getY()].removeArmy(actor.getArmyID());
        //Set Tile reference to rally point to null
        gameMap[actor.getLocation().getX()][actor.getLocation().getY()].removeRallyPoint(actor.getRp());
        //Set Army reference to rally point to null
        actor.setRallyPoint(null);



    }

    // Handle band army command
    public void handleBandArmyCmd(ArrayList<Unit> actors) {

//    	Location location = actors.get(0).location.getlocation();
//    	RallyPoint rp = new RallyPoint(location, this);
//    	Army newArmy = EntityFactory.getArmy(location, actors.get(0).getOwner(), rp, actors);
//    	players.get(actors.get(0).getOwner()).addArmy(newArmy);

        Location location = actors.get(0).getLocation();
        RallyPoint rp = EntityFactory.getRallyPoint(location, this, actors.get(0).getOwnerID());
        Army newArmy = EntityFactory.getArmy(location, actors.get(0).getOwnerID(),rp, actors);
        players.get(actors.get(0).getOwnerID()).addArmy(newArmy);
    }

    public void updateGameBoard(){
        for(int i = 0; i<gameMap.length;i++){
            for(int j = 0; j<gameMap.length;j++){
                gameMap[i][j].setOwnerID(-1);
            }
        }
        ArrayList<Unit> player0Unit = players.get(0).getAllUnit();
        for(int i = 0;i<player0Unit.size();i++){
            gameMap[player0Unit.get(i).getLocation().getX()][player0Unit.get(i).getLocation().getY()].setOwnerID(players.get(0).getPlayerID());
        }

        ArrayList<Unit> player1Unit = players.get(1).getAllUnit();
        for(int i = 0;i<player1Unit.size();i++){
            gameMap[player0Unit.get(i).getLocation().getX()][player0Unit.get(i).getLocation().getY()].setOwnerID(players.get(1).getPlayerID());
        }
    }
}
