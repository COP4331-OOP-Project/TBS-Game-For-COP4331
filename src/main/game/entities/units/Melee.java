package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Melee extends Unit
{
    public Melee(Location loc, int ownerID) {
       this.setAttackDamage(7);
       this.setDefenseDamage(7);
       this.setArmor(7);
       this.setHealth(10);
       this.setOrientation(1);
       this.setSpeed(3);
       this.setUpkeep(1f);
       this.setBaseResourceCost(10);
       this.setLocation(loc);
       this.setOwnerID(ownerID);
       this.setUnitType(1);
       this.setPowerMode(1);
       this.setDecomm(true);
    }

    public void addCommandToQueue(Command command){
        this.queue.add(command);
    }

    public void doTurn() {
        if(wait != 0){ this.wait--; }
    }

    public Command nextCommand(){ return this.queue.poll(); }

    public Command peekCommand(){ return this.queue.peek(); }

    public void cancelQueuedCommands(){ this.queue.clear(); }


    public void powerDown(){ this.setPowerMode(0); }

    public void powerUp(){
        this.setPowerMode(2);
        this.wait = 2;
    }

    public void decommissionEntity(){ this.setDecomm(false); }

}
