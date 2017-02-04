package game.entities.units;

import game.entities.structures.Structure;

public class Ranged extends Unit
{
  public Ranged(){}

  public Ranged(Structure base)
  {
    this.setAttackPower(7);
    this.setArmor(5);
    this.setHealth(10);
    this.setOrientation(1);
    this.setSpeed(5);
    this.setUpkeep(1f);
    this.setBaseResourceCost(10);
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.setUnitType(2);
    this.setUuid(UUID.randomUUID());
  }
}
