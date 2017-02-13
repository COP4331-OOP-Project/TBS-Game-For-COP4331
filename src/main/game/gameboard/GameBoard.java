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

    private Tile[][] gameMap;     // Map for game tiles  // 0 is grass, 1 is sand, 2 is water
    private ArrayList<Player> players;              // Players for game
    private static final int BOARD_SIZE = 42;
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
        init();                                     // Set initial units to initial tiles
    }

    private void init(){
        for(int i = 0; i<players.size();i++){
            ArrayList<Unit> playerUnit = players.get(i).getAllUnit();
            for(int n = 0; n<playerUnit.size();n++){
                gameMap[playerUnit.get(n).getLocation().getX()][playerUnit.get(n).getLocation().getY()].addUnit(playerUnit.get(n));
            }
        }
    }

    public Player getPlayer(int index) {
        if (index > players.size() - 1) return null;
        return this.players.get(0);
    }

    // Setup the gameMap array wit5h valid Tiles
    private void setupMap() {
        int[][] map = new int[][] {
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,1,0,1,1,2,2,2,2,2,2,2,2,2,2,2,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,1,1,1,0,1,1,2,2,2,2,2,2,2,2,2,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,1,1,0,1,0,1,1,2,2,2,2,2,2,2,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,1,1,0,0,0,0,0,1,2,2,2,2,2,2,2,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,1,1,0,1,0,0,0,1,2,2,2,2,2,2,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,1,1,0,0,1,0,0,1,2,2,2,2,2,2,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,2,2,2,1,1,1,0,0,0,1,2,2,2,2,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,2,2,2,2,2,1,1,0,0,1,0,1,2,2,2,2,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,1,1,1,2,2,2,2,2,0,1,0,1,0,0,1,2,2,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,1,0,1,1,1,2,2,0,1,1,0,0,0,0,2,2,2,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,1,0,0,1,1,1,0,0,1,1,0,0,0,1,2,2,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,1,1,0,1,1,0,0,0,0,1,1,0,1,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,1,1,1,0,0,0,1,0,1,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,1,0,2,2,0,1,0,1,0,0,1,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,1,0,2,2,2,2,0,0,1,0,0,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,1,0,2,2,2,2,0,0,1,0,0,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,2,2,0,1,0,2,2,2,2,0,1,0,0,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,1,0,2,0,1,0,1,0,0,0,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,1,1,1,0,2,0,0,0,0,0,0,0,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,0,0,1,2,2,2,0,0,1,1,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,0,0,0,1,0,0,2,0,0,1,0,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,1,0,0,0,0,1,0,0,0,0,2,1,1,1,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2,0,1,1,0,0,1,0,0,1,0,2,1,0,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,2,1,1,0,0,0,1,0,2,2,2,2,0,1,0,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,1,0,1,2,2,0,0,0,0,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,2,2,1,1,0,0,0,1,2,0,1,0,0,1,1,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,2,2,2,2,1,0,1,0,1,2,0,0,1,0,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,2,2,2,2,1,0,0,0,1,2,2,2,0,0,1,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,2,2,2,2,2,1,0,1,1,1,1,0,2,2,0,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,2,2,2,1,1,0,1,0,0,0,1,1,1,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,2,2,2,1,1,0,0,0,1,0,0,1,1,1,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,2,2,1,0,1,1,1,0,0,2,2,0,1,1,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,2,2,2,0,0,1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,2,2,1,0,0,1,0,1,0,0,1,1,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,2,2,2,1,1,0,1,0,0,1,0,1,0,0,1,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,2,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,2,2,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        };


      gameMap = new Tile[BOARD_SIZE][];
      for (int i = 0; i < BOARD_SIZE; i++)
        {
            gameMap[i] = new Tile[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE;j++)
            {
                Location l = new Location(i,j);
                if(map[i][j] == -1)
                    gameMap[i][j] = new Tile(TerrainEnum.INVISIBLE ,l);
                if(map[i][j] == 0)
                    gameMap[i][j] = new Tile(TerrainEnum.GRASS,l);
                if(map[i][j] == 1)
                    gameMap[i][j] = new Tile(TerrainEnum.SAND,l);
                if(map[i][j] == 2)
                    gameMap[i][j] = new Tile(TerrainEnum.WATER,l);
            }
        }
    }


    // Get specific tile with actor location
    public Tile getTileWithLocation(Location l) {
        return gameMap[l.getX()][l.getY()];
    }

    //Get the adjecent tile
    private Tile getAdjacentTile(Tile actorTile,int direction) {
        switch(direction) {
            case 0: //Move north
                return gameMap[actorTile.getLocation().getX()][actorTile.getLocation().getY() - 1];
            case 45: //move north east
                return gameMap[actorTile.getLocation().getX() + 1][actorTile.getLocation().getY() - 1];
            case 135: //move south east
                return gameMap[actorTile.getLocation().getX() + 1][actorTile.getLocation().getY()];
            case 180: //move south
                return gameMap[actorTile.getLocation().getX()][actorTile.getLocation().getY() + 1];
            case 225: //move south west
                return gameMap[actorTile.getLocation().getX() - 1][actorTile.getLocation().getY() + 1];
            case 315: //move north west
                return gameMap[actorTile.getLocation().getX() - 1][actorTile.getLocation().getY()];
            default:
                return null;
        }
    }

    public Tile[][] getTiles() {
        return this.gameMap;
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
            Army army = ((Army) actor);
            army.getBattleGroup().clear();
            army.getReinforcements().clear();
            army.groupDecomission(this);
            army.getAllUnits().clear();
            handleDisbandArmyCmd(((Army) actor));
        }
        else if(actor instanceof Unit){
            players.get(((Unit) actor).getOwnerID()).removeUnit(((Unit)actor));
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
            ( (Army) actor ).powerUp();
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
            ( (Army) actor ).powerDown();
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
            ( (Army) actor ).cancelQueuedCommands();
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
            //Call army to give attack command to all its units
            army.battlegroupAttack(this,direction);

        }
        else if(actor instanceof Structure) {

            Structure struct = (Structure) actor;

            Tile actorTile = getTileWithLocation(struct.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);
            targetTile.attackOccupants(((Structure) actor).getAttackDamage(), direction);

            // If queue is empty, pass another attack command for next turn
            if (struct.isQueueEmpty()) {
                AttackCommand<Structure> atkCmd = new AttackCommand<Structure>(this, struct, direction, 0);
                struct.addCommandToQueue(atkCmd);
            }

        }
        else if(actor instanceof Unit) {
            Unit unit = (Unit) actor;
            Tile actorTile = getTileWithLocation(unit.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);
            if(targetTile.hasEnemyUnit(unit.getOwnerID())){
                targetTile.attackOccupants(unit.getAttackDamage(),direction);
                for(Unit u : targetTile.getUnits()){
                    if(u.getHealth()<=0){
                        handleDecommissionCmd(u);
                    }
                }
            }
            unit.setDefenddirection(-1);
        }

    }

    // Handle defend command
    public <T> void handleDefendCmd(T actor, int direction) {

        // Find instance type and execute defense
        if(actor instanceof Army) {

            Army army = (Army) actor;

            army.battlegroupDefend(this, direction);
        }
        else if(actor instanceof Structure) {

            Structure struct = (Structure) actor;

            Tile actorTile = getTileWithLocation(struct.getLocation());
            Tile targetTile = getAdjacentTile(actorTile, direction);

        }
        else if(actor instanceof Unit) {
            Unit unit = (Unit) actor;
            unit.setDefenddirection(direction);
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
                //(army)actor.nextCommand();
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

            if(targetTile.hasEnemyUnit(unit.getOwnerID())) {
                unit.nextCommand();
            } else if (targetTile.isImpassable()) {
                log.error("Cannot move to tile, it is impassable!");
                unit.cancelQueuedCommands();
            }
            else {
                actorTile.removeUnit(unit.getUnitID());
                targetTile.addUnit(unit);
            }

            unit.setDefenddirection(-1);
        }
        else if (actor instanceof Structure) {
            System.out.println("structure cannot move");
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

                handleDecommissionCmd(actor);

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
        //Set army reference to rally point to null
        actor.setRallyPoint(null);
    }

    // Handle band army command
    public void handleBandArmyCmd(ArrayList<? extends Unit> actors) {

//    	Location location = actors.get(0).location.getlocation();
//    	rallyPoint rp = new rallyPoint(location, this);
//    	army newArmy = EntityFactory.getArmy(location, actors.get(0).getOwner(), rp, actors);
//    	players.get(actors.get(0).getOwner()).addArmy(newArmy);

        Location location = actors.get(0).getLocation();
        RallyPoint rp = EntityFactory.getRallyPoint(location, this, actors.get(0).getOwnerID());
        Army newArmy = EntityFactory.getArmy(actors.get(0).getOwnerID(), rp, actors);
        players.get(actors.get(0).getOwnerID()).addArmy(newArmy);
        players.get(actors.get(0).getOwnerID()).addRallyPoint(rp);
        gameMap[location.getX()][location.getY()].addArmy(newArmy);
        gameMap[location.getX()][location.getY()].addRallyPoint(rp);
    }

    public void updateGameBoard(){
        for(int i = 0; i<gameMap.length;i++){
            for(int j = 0; j<gameMap.length;j++){
                gameMap[i][j].setOwnerID(-1);
            }
        }

        for(int a = 0; a<2;a++) {
            ArrayList<Unit> playerUnit = players.get(a).getAllUnit();
            for (int i = 0; i < playerUnit.size(); i++) {
                gameMap[playerUnit.get(i).getLocation().getX()][playerUnit.get(i).getLocation().getY()].setOwnerID(players.get(a).getPlayerID());
            }
        }
    }
}
