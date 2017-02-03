package game.entities.units;
import game.entities.structures.Structure;

import java.io.*;

public class Worker extends Unit
{
  public Worker(){}

  public Worker(Structure base)
  {
    this.combatPower = 1;
    this.armor = 1;
    this.health = 10;
    this.orientation = 1;
    this.speed = 5;
    this.upkeep = 0.2f;
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.unitType = 5;
    this.uuid = UUID.randomUUID().toString();
  }
}