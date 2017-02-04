package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Worker extends Unit
{
  public Worker(){}

  public Worker(Location loc, int ownerID) {

    super(loc, ownerID, 5);  // Call super constructor
    this.setUuid(UUID.randomUUID());
    setUnitStats(1, 1, 1, 10, 1.0f, 10, 5, 1);

  }

}
