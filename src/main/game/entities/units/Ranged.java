package game.entities.units;


import controls.unit.UnitEnum;
import game.gameboard.Location;

public class Ranged extends Unit {

    // Constructor
    public Ranged(Location loc, int ownerID) {
        super(loc, ownerID, UnitEnum.RANGED);  // Call super constructor
        setStats(7, 2, 0, 10, 1, 5, 10);
    }

    public boolean canMake() {
        return false;
    }
}