package game.entities.units;


import game.gameboard.Location;

public class Explorer extends Unit {

  // Constructor
  public Explorer(Location loc, int ownerID) {
    super(loc, ownerID, 3);  // Call super constructor & set stats
    setStats(3, 3, 3, 10, 1, 9, 10);
  }
}
