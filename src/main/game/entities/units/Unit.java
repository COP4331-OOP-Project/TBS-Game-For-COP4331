package game.entities.units;

import game.entities.ICommandable;
import game.gameboard.Location;
import game.gameboard.Tile;
import game.commands.Command;
import java.io.*;
import java.util.*;

public abstract class Unit implements ICommandable
{
  private int attackDamage;
  private int defenseDamage;
  private int armor;
  private int health;
  private int orientation;
  private int speed;
  private float upkeep;
  private int baseResourceCost;
  private Location location;
  private int ownerID;
  private int unitType;
  private int powerMode;
  private int unitID;
  private boolean decomm;
  /*
    Power Modes:
        0 - Powered Down
        1 - Standby (When not part of an army)
        2 - Powered up (When part of an army)
        3 - Combat (Attacking/Defending)
   */

  protected Queue<Command> queue = new LinkedList<Command>();
  protected int wait;

  /* Accessors */
  public int getAttackDamage(){ return attackDamage; }
  public int getDefenseDamage(){ return defenseDamage; }
  public int getArmor(){ return armor; }
  public int getHealth(){ return health; }
  public int getOrientation(){ return orientation; }
  public int getSpeed(){ return speed; }
  public float getUpkeep(){ return upkeep; }
  public int getBaseResourceCost() { return baseResourceCost; }
  public Location getLocation(){ return location; }
  public int getOwnerID(){ return ownerID; }
  public int getUnitType(){ return unitType; }
  public int getPowerMode(){ return powerMode; }
  public int getUnitID(){ return unitID; }

  public int getResourceCost(){ return (int)(baseResourceCost * upkeep); }
  public Queue<Command> getQueue(){ return queue; }
  public boolean isDecomm(){ return decomm; }


  /* Mutators */
  public void setAttackDamage(int attackDamage) {this.attackDamage = attackDamage; }
  public void setDefenseDamage(int defenseDamage) {this.defenseDamage = defenseDamage; }
  public void setArmor(int a){ this.armor = a; }
  public void setHealth(int h){ this.health = h; }
  public void setOrientation(int o){ this.orientation = o; }
  public void setSpeed(int s){ this.speed = s; }
  public void setUpkeep(float u){ this.upkeep = u; }
  public void setBaseResourceCost(int cost) { this.baseResourceCost = cost; }
  public void setLocation(Location loc){ this.location = loc; }
  public void setOwnerID(int o){ this.ownerID = o; }
  public void setUnitType(int u){ this.unitType = u; }
  public void setPowerMode(int p){ this.powerMode = p; }
  public void setUnitID(int id){ this.unitID = id; }
  public void setDecomm(boolean d){ this.decomm = d;}

  protected void setUnitStats(int atk, int def, int armor, int hp, float upkeep, int resourceCost) {
      this.setAttackDamage(atk);
      this.setDefenseDamage(def);
      this.setArmor(armor);
      this.setHealth(hp);
      this.setUpkeep(upkeep);
      this.setBaseResourceCost(resourceCost);
  }

  public void printUnit()
  {
  	System.out.println("    Unit type: " + getUnitType());
  	System.out.println("	Armor: " + getArmor());
  	System.out.println("	Health: " + getHealth());
  	System.out.println("	Orientation: " + getOrientation());
  	System.out.println("	Speed: " + getSpeed());
  	System.out.println("	Upkeep: " + getUpkeep());
//  	System.out.println("	Location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
  }
}
