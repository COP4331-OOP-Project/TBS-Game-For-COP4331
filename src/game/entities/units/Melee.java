package src.game.entities.units;


import src.game.entities.structures.Structure;

public class Melee extends Unit
{
 public Melee(){}

 public Melee(Structure base)
 {
   this.setAttackPower(7);
   this.setArmor(7);
   this.setHealth(10);
   this.setOrientation(1);
   this.setSpeed(3);
   this.setUpkeep(1f);
   this.setBaseResourceCost(10);
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
   this.setUnitType(1);
   this.setUuid(UUID.randomUUID());
 }
}
