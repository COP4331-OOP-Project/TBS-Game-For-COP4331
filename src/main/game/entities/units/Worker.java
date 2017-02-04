package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;

import java.util.UUID;

public class Worker extends Unit
{
  public Worker(){}

  public Worker(Structure base)
  {
    this.setAttackDamage(1);
    this.setDefenseDamage(1);
    this.setArmor(1);
    this.setHealth(10);
    this.setOrientation(1);
    this.setSpeed(5);
    this.setUpkeep(1f);
    this.setBaseResourceCost(10);
//    this.location = base.getLocation();
//    this.owner = base.getOwner();
    this.setUnitType(5);
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
