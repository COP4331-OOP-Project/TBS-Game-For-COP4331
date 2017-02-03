package game.entities.structures;
import game.commands.Command;

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
    private Tile location;
    //TODO get queue to work!
//    private queue<Command> commands;

    public Structure(int ad, int dd, int armor, int health, int upkeep, int ownerID, Tile loc){
        attackDamage=ad;
        defenseDamage=dd;
        this.armor=armor;
        this.health=health;
        this.upkeep=upkeep;
        this.ownerID=ownerID;
        this.location = loc;
//        commands= new queue<Command>();
    }

//    public void insertCommand(Command command){
//        commands.add(command);
//    }

}
