package game.entities.units;
import game.entities.structures.Structure;

import java.io.*;

public class Ranged extends Unit
{
  public Ranged(){}

  public Ranged(Structure base)
  {
    this.combatPower = 7;
    this.armor = 5;
    this.health = 10;
    this.orientation = 1;
    this.speed = 5;
    this.upkeep = 0.2f;
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.unitType = "Ranged";
    this.id = 2;
    this.uuid = UUID.randomUUID().toString();
  }
}