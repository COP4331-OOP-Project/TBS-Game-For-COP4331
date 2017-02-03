package game.entities.units;
import game.entities.structures.Structure;

import java.io.*;

public class Explorer extends Unit
{
  public Explorer(){}

  public Explorer(Structure base)
  {
    this.combatPower = 3;
    this.armor = 3;
    this.health = 10;
    this.orientation = 1;
    this.speed = 9;
    this.upkeep = 0.2f;
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.unitType = "Explorer";
    this.id = 3;
    this.uuid = UUID.randomUUID().toString();
  }
}