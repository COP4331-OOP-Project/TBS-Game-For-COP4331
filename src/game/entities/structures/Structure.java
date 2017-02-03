package game.entities.structures;
import game.commands.Command;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by David on 2/1/2017.
 */
public abstract class Structure {
    private int attackDamage;
    private int defenseDamage;
    private int armor;
    private int health;
    private int upkeep;
    private int ownerID;
    private Location location;
    private Queue<Command> commands;

    public Structure(){}

    public Structure(int ad, int dd, int armor, int health, int upkeep, int ownerID, Location loc){
        attackDamage=ad;
        defenseDamage=dd;
        this.armor=armor;
        this.health=health;
        this.upkeep=upkeep;
        this.ownerID=ownerID;
        location=loc;
        commands= new LinkedList<Command>();
    }

    //Accessors
    public int getAttackDamage(){return attackDamage;}
    public int getDefenseDamage(){return defenseDamage;}
    public int getArmor(){return armor;}
    public int getHealth(){return health;}
    public int getUpkeep(){return upkeep;}
    public int getOwnerID(){return ownerID;}
    public Location getLocation(){return location;}
    //Command Queue Accessors
    public Command getNextCommand(){return commands.poll();}
    public boolean emptyQueue(){return commands.isEmpty();}

    //Setters
    public void setAttackDamage(int attackDamage) {this.attackDamage = attackDamage;}
    public void setDefenseDamage(int defenseDamage) {this.defenseDamage = defenseDamage;}
    public void setArmor(int armor) {this.armor = armor;}
    public void setHealth(int health) {this.health = health;}
    public void setUpkeep(int upkeep) {this.upkeep = upkeep;}
    public void setOwnerID(int ownerID) {this.ownerID = ownerID;}
    public void setLocation(Location location) {this.location = location;}
    //Command Queue Setters
    private void insertCommand(Command command){
        commands.add(command);
    }
    public void cancelQueuedOrders(){
        commands.clear();
    }


}
