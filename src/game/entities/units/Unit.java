package game.entities.units;
import game.gameboard.Tile;
import java.io.*;

public abstract class Unit
{
  private int combatPower;
  private int armor;
  private int health;
  private int orientation;
  private int speed;
  private float upkeep;
  private int baseResourceCost;
  private Tile location;
  private char owner;
  private int unitType;
  private UUID uuid;


  public Unit(){}

  /* Accessors */
  public int getCombatPower(){ return this.combatPower; }
  public int getArmor(){ return this.armor; }
  public int getHealth(){ return this.health; }
  public int getOrientation(){ return this.orientation; }
  public int getSpeed(){ return this.speed; }
  public float getUpkeep(){ return this.upkeep; }
  public int getBaseResourceCost() { return this.baseResourceCost; }
  public Tile getLocation(){ return this.location; }
  public char getOwner(){ return this.owner; }
  public int getUnitType(){ return this.unitType; }
  public String getUuid(){ return this.uuid; }
  public int getResourceCost() { return (int)(this.baseResourceCost * this.upkeep); }



  /* Mutators */
  public void setCombatPower(int cp){ this.combatPower = cp; }
  public void setArmor(int a){ this.armor = a; }
  public void setHealth(int h){ this.health = h; }
  public void setOrientation(int o){ this.orientation = o; }
  public void setSpeed(int s){ this.speed = s; }
  public void setUpkeep(float u){ this.upkeep = u; }
  public void setBaseResourceCost(int cost) { this.baseResourceCost = cost; }
  public void setLocation(Tile t){ this.location = t; }
  public void setOwner(char o){ this.owner = o; }
  public void setUnitType(String u){ this.unitType = u; }


  public void printUnit()
  {
  	System.out.println("Unit type: " + this.getUnitType());
  	System.out.println("	Combat Power: " + this.getCombatPower());
  	System.out.println("	Armor: " + this.getArmor());
  	System.out.println("	Health: " + this.getHealth());
  	System.out.println("	Orientation: " + this.getOrientation());
  	System.out.println("	Speed: " + this.getSpeed());
  	System.out.println("	Upkeep: " + this.getUpkeep());
//  	System.out.println("	Location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
  	System.out.println("	Owner: " + this.getOwner());
  }
}
