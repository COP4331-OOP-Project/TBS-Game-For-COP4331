package game.entities.units;
import game.entities.ICommandable;
import game.gameboard.Tile;
import java.io.*;
import java.util.UUID;

public abstract class Unit implements ICommandable
{
  private UUID uuid;
  private int attackDamage;
  private int defenseDamage;
  private int armor;
  private int health;
  private int orientation;
  private int speed;
  private float upkeep;
  private int baseResourceCost;
  private Tile location;
  private int ownerID;
  private int unitType;

  public Unit(){}

  /* Accessors */
  public int getAttackDamage(){ return this.attackDamage; }
  public int getDefenseDamage(){ return this.defenseDamage; }
  public int getArmor(){ return this.armor; }
  public int getHealth(){ return this.health; }
  public int getOrientation(){ return this.orientation; }
  public int getSpeed(){ return this.speed; }
  public float getUpkeep(){ return this.upkeep; }
  public int getBaseResourceCost() { return this.baseResourceCost; }
  public Tile getLocation(){ return this.location; }
  public int getOwnerID(){ return this.ownerID; }
  public int getUnitType(){ return this.unitType; }
  public UUID getUuid(){ return this.uuid; }
  public int getResourceCost() { return (int)(this.baseResourceCost * this.upkeep); }

  /* Mutators */
  public void setAttackDamage(int attackDamage) {this.attackDamage = attackDamage; }
  public void setDefenseDamage(int defenseDamage) {this.defenseDamage = defenseDamage; }
  public void setArmor(int a){ this.armor = a; }
  public void setHealth(int h){ this.health = h; }
  public void setOrientation(int o){ this.orientation = o; }
  public void setSpeed(int s){ this.speed = s; }
  public void setUpkeep(float u){ this.upkeep = u; }
  public void setBaseResourceCost(int cost) { this.baseResourceCost = cost; }
  public void setLocation(Tile t){ this.location = t; }
  public void setOwnerID(int o){ this.ownerID = o; }
  public void setUnitType(int u){ this.unitType = u; }
  public void setUuid(UUID id){ this.uuid = id; }

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
  	System.out.println("Unit type: " + this.getUnitType());
  	System.out.println("	Armor: " + this.getArmor());
  	System.out.println("	Health: " + this.getHealth());
  	System.out.println("	Orientation: " + this.getOrientation());
  	System.out.println("	Speed: " + this.getSpeed());
  	System.out.println("	Upkeep: " + this.getUpkeep());
//  	System.out.println("	Location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
  }
}
