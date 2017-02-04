package game.entities.units;


import game.entities.structures.Structure;

public class Worker extends Unit
{
  public Worker(){}

  public Worker(Structure base)
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
    this.setUnitType(5);
    this.setUuid(UUID.randomUUID());
  }
}
