package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Explorer extends Unit
{
  public Explorer(){}

  public Explorer(Location loc, int ownerID) {

    super(loc, ownerID, 3);  // Call super constructor
    this.setUuid(UUID.randomUUID());
    setUnitStats(3, 3, 3, 10, 1.0f, 10, 9, 1);

  }

}
