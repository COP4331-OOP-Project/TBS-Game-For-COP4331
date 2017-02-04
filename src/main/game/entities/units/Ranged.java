package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Ranged extends Unit
{
  public Ranged(){}

  public Ranged(Location loc, int ownerID) {

    super(loc, ownerID, 2);  // Call super constructor
    this.setUuid(UUID.randomUUID());
    setUnitStats(7, 5, 5, 10, 1.0f, 10, 5, 1);

  }

}
