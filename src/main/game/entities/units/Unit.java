package game.entities.units;
import game.commands.Command;
import game.entities.ICommandable;
import game.gameboard.Location;
import game.gameboard.Tile;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
  private Location location;
  private int poweredState;
  private int ownerID;
  private int unitType;

  private Queue<Command> commands;

  // Base constructor
  public Unit() {}

  // Constructor
  public Unit(Location loc, int ownerId, int unitType) {
    this.location = loc;
    this.ownerID = ownerId;
    this.unitType = unitType;
    this.commands = new LinkedList<Command>();
  }

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
  public void setUuid(UUID id){ this.uuid = id; }

  // Setup unit stats
  protected void setUnitStats(int atk, int def, int armor, int hp, float upkeep, int resourceCost, int speed, int orientation) {
      this.setAttackDamage(atk);
      this.setDefenseDamage(def);
      this.setArmor(armor);
      this.setHealth(hp);
      this.setUpkeep(upkeep);
      this.setBaseResourceCost(resourceCost);
      this.setOrientation(orientation);
      this.setSpeed(speed);
  }

  // Set powered state of unit
  private void setPoweredState(int poweredState) {
    this.poweredState = poweredState;
    this.upkeep = ((poweredState == 1) ? 1.0f : 0.25f);
  }

  // Printout unit
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

  // Command interface handlers

  // Perform next turn at beginning of player turn
  public void doTurn() {
    if (peekCommand().getDuration() == 0) {
      nextCommand().exec();
    }
    else peekCommand().iterateDuration();
  }


  // Commandable actions

  public Command nextCommand() { return commands.poll(); }                    // Look at next command
  public Command peekCommand() { return commands.peek(); }                    // Pop off next command
  public boolean isQueueEmpty() { return commands.isEmpty(); }                  // Pop off next command
  public void addCommandToQueue(Command command){
    commands.add(command);
  }   // Add next cmd to queue
  public void cancelQueuedCommands() {
    commands.clear();
  }                    // Clear queue
  public void powerUp() { setPoweredState(1); }                               // Power down state
  public void powerDown() { setPoweredState(0); }                             // Power up state
  public void decommissionEntity() {}                                         // Destroy struct & remove refs

}
