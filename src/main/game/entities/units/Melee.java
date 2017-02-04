package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Melee extends Unit
{
    public Melee(){}

    public Melee(Location loc, int ownerID) {
       this.setAttackDamage(7);
       this.setArmor(7);
       this.setHealth(10);
       this.setOrientation(1);
       this.setSpeed(3);
       this.setUpkeep(1f);
       this.setBaseResourceCost(10);
       this.setLocation(loc);
       this.setOwnerID(ownerID);
       this.setUnitType(1);
       this.setUuid(UUID.randomUUID());
     }

    public void addCommandToQueue(Command command) {

    }

    public void doTurn() {

    }

    public Command nextCommand() {
        // TODO: fix me
        return null;
    }

    public Command peekCommand() {
        // TODO: fix me
        return null;
    }

    public void cancelQueuedCommands() {

    }

    public void powerDown() {

    }

    public void powerUp() {

    }

    public void decommissionEntity() {

    }
}
