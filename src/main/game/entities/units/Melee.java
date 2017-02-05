package game.entities.units;


import game.gameboard.Location;

public class Melee extends Unit {

    // Constructor
    public Melee(Location loc, int ownerID) {
        super(loc, ownerID, 0);  // Call super constructor & set stats
        setStats(7, 7, 7, 10, 1, 3, 10);
    }

    public boolean canMake() {
        return false;
    }
}