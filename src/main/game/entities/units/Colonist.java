package game.entities.units;


import game.entities.structures.Structure;

import java.util.UUID;

public class Colonist extends Unit
{
  public Colonist(){}

  public Colonist(Structure base)
  {
    this.setAttackPower(1);
    this.setArmor(1);
    this.setHealth(10);
    this.setOrientation(1);
    this.setSpeed(5);
    this.setUpkeep(1f);
    this.setBaseResourceCost(10);
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.setUnitType(4);
    this.setUuid(UUID.randomUUID());
  }
}
