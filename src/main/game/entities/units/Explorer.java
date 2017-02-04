package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Explorer extends Unit
{
  public Explorer(){}

  public Explorer(Location loc, int ownerID) {
    this.setAttackDamage(3);
    this.setArmor(3);
    this.setHealth(10);
    this.setOrientation(1);
    this.setSpeed(9);
    this.setUpkeep(1f);
    this.setBaseResourceCost(10);
    this.setLocation(loc);
    this.setOwnerID(ownerID);
    this.setUnitType(3);
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
