package game.entities.structures;
import game.commands.Command;
import game.entities.ICommandable;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * Created by David on 2/1/2017.
 */
public abstract class Structure implements ICommandable {

    private int structureID;
    private int attackDamage;
    private int defenseDamage;
    private int armor;
    private int health;
    private float upkeep;
    private int baseResourceCost;
    private int ownerID;
    private int poweredState;
    private Location location;
    private Queue<Command> commands;

    // Constructor
    public Structure(Location loc, int ownerID) {
        this.location=loc;
        this.ownerID=ownerID;
        commands= new LinkedList<Command>();
    }


    //Command Queue Accessors
    public Command getNextCommand(){return commands.poll();}
    public boolean emptyQueue(){return commands.isEmpty();}

    // Setup struct stats based on passed values of constructed structure
    protected void setStructureStats(int atk, int def, int armor, int hp, float upkeep, int resourceCost) {
        this.setAttackDamage(atk);
        this.setDefenseDamage(def);
        this.setArmor(armor);
        this.setHealth(hp);
        this.setUpkeep(upkeep);
    }

    //Accessors

    // Getters
    public int getAttackDamage(){return attackDamage;}
    public int getDefenseDamage(){return defenseDamage;}
    public int getArmor(){return armor;}
    public int getHealth(){return health;}
    public float getUpkeep(){return upkeep;}
    public int getOwnerID(){return ownerID;}
    public Location getLocation(){return location;}
    public int getPoweredState() { return poweredState; }
    public int getBaseResourceCost() { return baseResourceCost; }
    public int getResourceCost() { return (int) (baseResourceCost * upkeep); }
    public int getStructureID() {return structureID;}

    //Setters
    public void setAttackDamage(int attackDamage) {this.attackDamage = attackDamage;}
    public void setDefenseDamage(int defenseDamage) {this.defenseDamage = defenseDamage;}
    public void setArmor(int armor) {this.armor = armor;}
    public void setHealth(int health) {this.health = health;}
    public void setUpkeep(float upkeep) {this.upkeep = upkeep;}
    public void setOwnerID(int ownerID) {this.ownerID = ownerID;}
    public void setLocation(Location location) {this.location = location;}
    public void setBaseResourceCost(int cost) { this.baseResourceCost = cost; }
    public void setStructureID(int structureID){this.structureID = structureID;}
    private void setPoweredState(int poweredState) {
        this.poweredState = poweredState;
        this.upkeep = ((poweredState == 1) ? 1.0f : 0.25f);
    }

    // Command interface handlers

    // Perform next turn at beginning of player turn
    public void doTurn() {
        if (peekCommand().getDuration() == 0) {
           nextCommand().exec();
        }
        else peekCommand().iterateDuration();
    }

    public Command nextCommand() { return commands.poll(); }                    // Look at next command
    public Command peekCommand() { return commands.peek(); }                    // Pop off next command
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
