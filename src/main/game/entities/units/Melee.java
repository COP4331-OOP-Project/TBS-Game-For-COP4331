package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Melee extends Unit
{
    public Melee(Location loc, int ownerID) {
       setAttackDamage(7);
       setDefenseDamage(7);
       setArmor(7);
       setHealth(10);
       setOrientation(1);
       setSpeed(3);
       setUpkeep(1f);
       setBaseResourceCost(10);
       setLocation(loc);
       setOwnerID(ownerID);
       setUnitType(0);
       setPowerMode(1);
       setDecomm(true);
    }

    public void addCommandToQueue(Command command){
        queue.offer(command);
    }

    public void doTurn() {
        if(wait != 0){ wait--; }
    }

    public Command nextCommand(){ return queue.poll(); }

    public Command peekCommand(){ return queue.peek(); }

    public void cancelQueuedCommands(){ queue.clear(); }

    public void powerDown(){ setPowerMode(0); }

    public void powerUp(){
        this.setPowerMode(2);
        this.wait = 2;
    }

    public void decommissionEntity(){ setDecomm(false); }

}