package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Explorer extends Unit
{
  public Explorer(Location loc, int ownerID) {
    setAttackDamage(3);
    setDefenseDamage(3);
    setArmor(3);
    setHealth(10);
    setOrientation(1);
    setSpeed(9);
    setUpkeep(1f);
    setBaseResourceCost(10);
    setLocation(loc);
    setOwnerID(ownerID);
    setUnitType(3);
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
