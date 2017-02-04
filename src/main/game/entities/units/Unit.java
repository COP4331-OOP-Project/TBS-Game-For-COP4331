package game.entities.units;

import game.entities.ICommandable;
import game.commands.CommandQueue;
import game.gameboard.Location;
import game.gameboard.Tile;
import java.io.*;
import java.util.UUID;

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

  protected CommandQueue queue = new CommandQueue();
  protected int wait;

  /* Accessors */
  public int getAttackDamage(){ return this.attackDamage; }
  public int getDefenseDamage(){ return this.defenseDamage; }
  public int getArmor(){ return this.armor; }
  public int getHealth(){ return this.health; }
  public int getOrientation(){ return this.orientation; }
  public int getSpeed(){ return this.speed; }
  public float getUpkeep(){ return this.upkeep; }
  public int getBaseResourceCost() { return this.baseResourceCost; }
  public Location getLocation(){ return this.location; }
  public int getOwnerID(){ return this.ownerID; }
  public int getUnitType(){ return this.unitType; }
  public int getPowerMode(){ return this.powerMode; }
  public int getUnitID(){ return this.unitID; }

  public int getResourceCost(){ return (int)(this.baseResourceCost * this.upkeep); }
  public CommandQueue getQueue(){ return this.queue; }
  public boolean isDecomm(){ return this.decomm; }


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
  	System.out.println("    Unit type: " + this.getUnitType());
  	System.out.println("	Armor: " + this.getArmor());
  	System.out.println("	Health: " + this.getHealth());
  	System.out.println("	Orientation: " + this.getOrientation());
  	System.out.println("	Speed: " + this.getSpeed());
  	System.out.println("	Upkeep: " + this.getUpkeep());
//  	System.out.println("	Location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
  }
}
