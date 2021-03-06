package game.gameboard;

import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;

import java.util.ArrayList;

/**
 * Created by David on 2/1/2017.
 */
public class Tile implements ITileAccessors {
    public boolean containsUnit;
    public boolean containsArmy;
    public boolean containsRallyPoint;
    public boolean containsStructure;
    //Leave out AreaOfEffect, Resources, Item for iteration 1
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Location location;
    private int ownerID;

    //Constructors
    Tile(TerrainEnum tileType, Location location) {
        Terrain = tileType;
        units = new ArrayList<Unit>();
        armies = new ArrayList<Army>();
        rallyPoints = new ArrayList<RallyPoint>();
        containsStructure = false;
        containsRallyPoint = false;
        containsUnit = false;
        containsArmy = false;
        structure = null;
        this.location = location;
        this.setOwnerID(-1);
    }

    // Get Tile Location
    public Location getLocation() {
        return location;
    }

    // Add unit to Tile
    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setLocation(this.location);
        containsUnit = true;
    }

    // Test if terrain is impassable
    public boolean isImpassable() {
        return (Terrain == TerrainEnum.INVISIBLE || Terrain == TerrainEnum.WATER) ? true : false;
    }

    // Get Tile terrain type
    public TerrainEnum getTileType() {
        return Terrain;
    }

    // Remove unit from tile by ID
    public void removeUnit(int unitID) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                units.remove(i);
            }
        }

        if (units.isEmpty()) {
            containsUnit = false;
        }
    }

    public void removeAllUnits() {
        this.units.clear();
    }

    public ArrayList<RallyPoint> getRallyPoints() {
        return rallyPoints;
    }

    public void addRallyPoint(RallyPoint rp) {
        rallyPoints.add(rp);
        rp.setLocation(location);
        containsRallyPoint = true;
    }

    public void removeRallyPoint(RallyPoint rp) {
        for (int i = 0; i < rallyPoints.size(); i++) {
            if (rallyPoints.get(i).getRallyID() == rp.getRallyID()) {
                rallyPoints.remove(i);
                break;
            }
        }
        if (rallyPoints.isEmpty()) {
            containsRallyPoint = false;
        }
    }


    // Get all units from Tile
    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Add army to Tile
    public void addArmy(Army army) {
        armies.add(army);
        containsArmy = true;
    }

    public void removeArmy(int armyID) {
        for (int i = 0; i < armies.size(); i++) {
            if (armies.get(i).getArmyID() == armyID) {
                armies.remove(i);
                break;
            }
        }
        if (armies.isEmpty()) {
            containsArmy = false;
        }
    }

    public boolean containsBattleGroup() {
        boolean hasBattleGroup = false;
        for (RallyPoint rallyPoint : this.getRallyPoints()) {
            if (rallyPoint.hasBattleGroupAtLocation()) {
                hasBattleGroup = true;
            }
        }
        return hasBattleGroup;
    }

    // Get all armies from Tile
    public ArrayList<Army> getArmies() {
        return armies;
    }

    // Remove structure from tile
    public void removeStructure() {
        this.structure = null;
        containsStructure = false;
    }

    // Get structure instance from Tile
    public Structure getStructure() {
        return structure;
    }

    // Set structure on tile
    public void setStructure(Structure structure) {
        this.structure = structure;
        containsStructure = true;
    }

    // Damage all units and structure on tile
    public void attackOccupants(int damage, int direction) {

        // Damage all units
        if (containsUnit) {
            int reduction = 0;
            for (Unit u : units) {
                reduction = u.getArmor();
                if ((direction + 180) % 360 == u.getDefenddirection()) {
                    reduction = reduction + u.getDefenseDamage();
                }
                if (damage - reduction > 0) {
                    u.setHealth(u.getHealth() - damage);
                }
            }
        }
        // Damage structure
        if (containsStructure) {
            structure.setHealth(structure.getHealth() - damage);
        }
    }

    // Heal all units and structure on tile
    public void healOccupants(int value) {

        // Heal all units
        for (Unit u : units) {
            u.setHealth(u.getHealth() + value);
        }

        // Heal structure
        structure.setHealth(structure.getHealth() + value);

    }

    public int getOwnerID() {
        return this.ownerID;
    }

    // OwnerID Accessor
    public void setOwnerID(int playerID) {
        this.ownerID = playerID;
    }

    // Return unit of given id
    public Unit getUnitById(int id) throws EntityNotFoundException {
        for (Unit u : units) {
            if (u.getUnitID() == id) return u;
        }

        throw new EntityNotFoundException("unit of id " + id + " " +
                "not found on Tile " + location.getX() + ", " + location.getY());
    }

    // Return army of given id
    public Army getArmyById(int id) throws EntityNotFoundException {
        for (Army a : armies) {
            if (a.getArmyID() == id) return a;
        }

        throw new EntityNotFoundException("army of id " + id + " " +
                "not found on Tile " + location.getX() + ", " + location.getY());
    }

    // Check whether this tile is occupied by enemy unit
    public boolean hasEnemyUnit(int playerID) {
        if (this.ownerID == playerID)
            return false;
        else if (this.ownerID == -1) {
            return false;
        } else if (playerID != this.ownerID && this.ownerID != -1) {
            return true;
        }

        return false;
    }
}
