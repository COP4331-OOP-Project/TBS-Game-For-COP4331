package game.entities.units;


import game.gameboard.Location;

public class Ranged extends Unit {

  // Constructor
  public Ranged(Location loc, int ownerID) {
  super(loc, ownerID, 1);  // Call super constructor
    setStats(15, 5, 5, 15, 1, 5, 10);
  }

  public boolean canMake() {
    return false;
  }
}