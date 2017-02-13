package game.entities.units;

import controls.unit.UnitEnum;
import game.gameboard.Location;

public class Colonist extends Unit {

  // Constructor
  public Colonist(Location loc, int ownerID){
    super(loc, ownerID, UnitEnum.COLONIST);  // Call super constructor & set stats
    setStats(1, 1, 1, 10, 1, 5, 10);
  }

  public boolean canMake() {
    return true;
  }
}
