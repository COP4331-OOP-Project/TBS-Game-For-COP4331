package game.entities.units;
import game.entities.structures.Structure;

import java.io.*;

public class Melee extends Unit
{
 public Melee(){}

 public Melee(Structure base)
 {
  this.combatPower = 7;
  this.armor = 7;
  this.health = 10;
  this.orientation = 1;
  this.speed = 3;
  this.upkeep = 0.2f;
//  this.location = base.getLocation();
//  this.owner = base.getOwner();
  this.unitType = 1;
  this.uuid = UUID.randomUUID().toString();
}
}