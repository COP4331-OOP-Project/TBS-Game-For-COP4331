package game.entities.units;
import game.entities.structures.Structure;

import java.io.*;

public class Explorer extends Unit
{
  public Explorer(){}

  public Explorer(Structure base)
  {
    this.setAttackPower(3);
    this.setArmor(3);
    this.setHealth(10);
    this.setOrientation(1);
    this.setSpeed(9);
    this.setUpkeep(1f);
    this.setBaseResourceCost(10);
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.setUnitType(3);
    this.setUuid(UUID.randomUUID());
  }
}
