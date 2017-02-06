package game.gameboard;

import game.entities.Army;
import game.entities.structures.Structure;
import game.entities.units.Unit;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

/**
 * Necessary commands for Tile access and management
 */
public interface ITileAccessors {

    // Tile attribute accessors
    Location getLocation();
    int getTileType();
    int getOwnerID();

    // Tile attribute setters
    void setOwnerID(int playerID);

    // Boolean testers
    boolean hasEnemyUnit(int playerID);
    boolean isImpassable();

    // Get entity commands
    Unit getUnitById(int id) throws EntityNotFoundException;
    Army getArmyById(int id) throws EntityNotFoundException;
    ArrayList<Unit> getUnits();
    ArrayList<Army> getArmies();
    Structure getStructure();

    // Remove entity commands
    void removeArmy(int armyID);
    void removeStructure();
    void removeUnit(int unitID);

    // Add entity commands
    void addUnit(Unit unit);
    void addArmy(Army army);
    void setStructure(Structure structure);

    // Interaction handling
    void attackOccupants(int damage, int direction);
    void healOccupants(int value);


}
