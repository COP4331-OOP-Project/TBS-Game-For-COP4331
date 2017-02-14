package game.entities.units;


import controls.unit.UnitEnum;
import game.gameboard.Location;

public class Melee extends Unit {

    // Constructor
    public Melee(Location loc, int ownerID) {
        super(loc, ownerID, UnitEnum.MELEE);  // Call super constructor & set stats
        setStats(7, 2, 2, 10, 1, 3, 10);
    }

    public boolean canMake() {
        return false;
    }
}