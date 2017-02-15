package game.entities.units;


import controls.unit.UnitEnum;
import game.gameboard.Location;

public class Explorer extends Unit {

  // Constructor
  public Explorer(Location loc, int ownerID) {
    super(loc, ownerID, UnitEnum.EXPLORER);  // Call super constructor & set stats
    setStats(3, 2, 1, 10, 1, 9, 10);
  }

  public boolean canMake() {
    return false;
  }
}
