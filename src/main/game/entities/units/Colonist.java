package game.entities.units;


import game.commands.AttackCommand;
import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Colonist extends Unit
{
  public Colonist(){}

  public Colonist(Location loc, int ownerID) {

    super(loc, ownerID, 4);  // Call super constructor
    this.setUuid(UUID.randomUUID());
    setUnitStats(1, 1, 1, 10, 1.0f, 10, 5, 1);

  }

}
