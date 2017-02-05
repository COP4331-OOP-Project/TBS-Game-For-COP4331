package game.entities.structures;
import game.commands.Command;
import game.entities.ICommandable;
import game.entities.PowerState;
import game.entities.TileOccupant;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by David on 2/1/2017.
 */
public abstract class Structure implements ICommandable {

    private int structureID;                            // Unique structure id (0-9)
    private int ownerID;                                // Player ownder id for structure
    private PowerState powerState;                      // Structure power state
    private Location location;                          // Structure location

    private Queue<Command> queue;                       // Structure command queue

    // Structure stats
    private int attackDamage;
    private int defenseDamage;
    private int armor;
    private int health;
    private float upkeep;
    private int baseResourceCost;

    // Constructor
    public Structure(Location loc, int ownerID) {
        this.location=loc;
        this.ownerID=ownerID;
        setPowerState(PowerState.POWERED_UP);
        queue = new LinkedList<Command>();
    }

    // Setup struct stats based on passed values of constructed structure
    protected void setStats(int atk, int def, int armor, int hp, int baseResourceCost) {
        this.attackDamage = atk;
        this.defenseDamage = def;
        this.armor = armor;
        this.health = hp;
        this.baseResourceCost = baseResourceCost;
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
    public PowerState getPowerState() { return powerState; }
    public int getBaseResourceCost() { return baseResourceCost; }
    public float getResourceCost() { return (baseResourceCost * upkeep); }
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


    // Command interface handlers

    // Set value of powerState for unit
    public void setPowerState(PowerState state) {

        this.powerState = state;            // Set state
        this.upkeep = state.getUpkeep();    // Set value of state upkeep

    }


    // Perform next turn at beginning of player turn
    public void doTurn() {

        if (!queue.isEmpty()) {
            if (peekCommand().getDuration() == 0) {                               // Test if next cmd can fire
                nextCommand().exec();                                               // Execute next cmd
            }
            else peekCommand().iterateDuration();
        }

    }


    // Commandable actions

    public Command nextCommand() { return queue.poll(); }                       // Look at next command
    public Command peekCommand() { return queue.peek(); }                       // Pop off next command
    public boolean isQueueEmpty() { return queue.isEmpty(); }                   // Pop off next command
    public void addCommandToQueue(Command command){ queue.add(command); }       // Add next cmd to queue
    public void cancelQueuedCommands() { queue.clear(); }                       // Clear queue
    public void powerUp() { setPowerState(PowerState.POWERED_UP); }             // Power down state
    public void powerDown() { setPowerState(PowerState.POWERED_DOWN); }         // Power up state
    public void combatState() { setPowerState(PowerState.COMBAT); }             // Combat state
    public void standby() { setPowerState(PowerState.STANDBY); }                // Standby state

    // TODO: Setup decommission entity
    public void decommissionEntity() {}                                       // Destroy struct & remove refs


    public boolean canMake() { return true; };
    public boolean canHeal() { return false; }
    public boolean canAttack() { return true; }
    public boolean canDefend() { return true; }
    public boolean canPowerUp() { return true; }
    public boolean canPowerDown() { return true; }
    public boolean canCancelCommandQueue() { return true; }
    public boolean canDecomission() { return true; }
    public boolean canMove() { return false; }
}
