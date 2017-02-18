package game.entities.units;

import controls.unit.UnitEnum;
import game.commands.Command;
import game.entities.ICommandable;
import game.entities.PowerState;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Unit implements ICommandable {

    protected Queue<Command> queue = new LinkedList<Command>(); // unit command queue
    private int unitID;               // unit id (0 - 9 of instance type)
    private int ownerID;              // Player's id
    private Location location;        // unit location
    private PowerState powerState;    // unit power state
    private UnitEnum unitType;             // unit's type
    // unit stats
    private int attackDamage;
    private int defenseDamage;
    private int armor;
    private int health;
    private int orientation;
    private int speed;
    private float upkeep;
    private int baseResourceCost;
    private int defenddirection;

    // Constructor
    public Unit(Location loc, int ownerID, UnitEnum unitType) {
        this.location = loc;
        this.ownerID = ownerID;
        this.unitType = unitType;
        this.defenddirection = -1;
        setPowerState(PowerState.POWERED_UP);
    }

    // Setup unit base stats from type
    protected void setStats(int atk, int def, int armor, int hp, int orientation, int spd, int baseResourceCost) {
        this.attackDamage = atk;
        this.defenseDamage = def;
        this.armor = armor;
        this.health = hp;
        this.orientation = orientation;
        this.speed = spd;
        this.baseResourceCost = baseResourceCost;
    }

    /* Accessors */
    public int getAttackDamage() {
        return attackDamage;
    }

    /* Mutators */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getDefenseDamage() {
        return defenseDamage;
    }

    public void setDefenseDamage(int defenseDamage) {
        this.defenseDamage = defenseDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int a) {
        this.armor = a;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int o) {
        this.orientation = o;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s) {
        this.speed = s;
    }

    public float getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(float u) {
        this.upkeep = u;
    }

    public int getBaseResourceCost() {
        return baseResourceCost;
    }

    public void setBaseResourceCost(int cost) {
        this.baseResourceCost = cost;
    }

    public PowerState getPowerState() {
        return powerState;
    }

    // Set value of powerState for unit
    public void setPowerState(PowerState state) {

        this.powerState = state;            // Set state
        this.upkeep = state.getUpkeep();    // Set value of state upkeep

    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int id) {
        this.unitID = id;
    }

    public float getResourceCost() {
        return (baseResourceCost * upkeep);
    }

    public Queue<Command> getQueue() {
        return queue;
    }

    public int getDefenddirection() {
        return defenddirection;
    }

    public void setDefenddirection(int defenddirection) {
        this.defenddirection = defenddirection;
    }

    public UnitEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitEnum u) {
        this.unitType = u;
    }

    public void printUnit() {
        System.out.println("    Unit Type: " + getUnitType());
        System.out.println("	Armor: " + getArmor());
        System.out.println("	Health: " + getHealth());
        System.out.println("	Orientation: " + getOrientation());
        System.out.println("	Speed: " + getSpeed());
        System.out.println("	Upkeep: " + getUpkeep());
//  	System.out.println("	Location: " + this.getLocation().getX() + ", " + this.getLocation().getY());
    }

    // Perform next turn at beginning of player turn
    public void doTurn() {

        if (!queue.isEmpty()) {
            if (peekCommand().getDuration() == 0) {                               // Test if next cmd can fire
                nextCommand().exec();                                               // Execute next cmd
            } else peekCommand().iterateDuration();
        }

    }

    // Command interface handlers

    public Command nextCommand() {
        return queue.poll();
    }                     // Look at next command

    public Command peekCommand() {
        return queue.peek();
    }                     // Pop off next command


    // Commandable actions

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }                 // Pop off next command

    public void addCommandToQueue(Command command) {
        queue.add(command);
    }     // Add next cmd to queue

    public void cancelQueuedCommands() {
        queue.clear();
    }                     // Clear queue

    public void powerUp() {
        setPowerState(PowerState.POWERED_UP);
    }           // Power down state

    public void powerDown() {
        setPowerState(PowerState.POWERED_DOWN);
    }       // Power up state

    public void combatState() {
        setPowerState(PowerState.COMBAT);
    }           // Combat state

    public void standby() {
        setPowerState(PowerState.STANDBY);
    }              // Standby state

    public int getOwnerID() {
        return ownerID;
    }                                // Get owner id

    public void setOwnerID(int o) {
        this.ownerID = o;
    }

    public Location getLocation() {
        return location;
    }                         // Get location

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public abstract boolean canMake();

    public boolean canHeal() {
        return false;
    }

    public boolean canAttack() {
        return true;
    }

    public boolean canDefend() {
        return true;
    }

    public boolean canPowerUp() {
        return true;
    }

    public boolean canPowerDown() {
        return true;
    }

    public boolean canCancelCommandQueue() {
        return true;
    }

    public boolean canDecomission() {
        return true;
    }

    public boolean canMove() {
        return true;
    }


    // TODO: Setup decommission entity
    public void decommissionEntity() {
    }                                       // Destroy struct & remove refs

}
