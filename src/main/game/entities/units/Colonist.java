package game.entities.units;


import game.gameboard.Location;

public class Colonist extends Unit {

  // Constructor
  public Colonist(Location loc, int ownerID){
    super(loc, ownerID, 3);  // Call super constructor & set stats
    setStats(0, 0, 1, 10, 1, 5, 10);
  }

  public boolean canMake() {
    return true;
  }
}
