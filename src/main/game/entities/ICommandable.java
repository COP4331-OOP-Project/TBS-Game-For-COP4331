package game.entities;

import game.commands.Command;
import game.gameboard.Location;

/**
 * Interface for basic commands PowerUp, PowerDown, Decommission,
 */
public interface ICommandable {

    // Command queue management
    void addCommandToQueue(Command command);    // Add new command to queue

    void doTurn();                              // Iterate turn

    Command nextCommand();                      // Next queue for new command or decrement turn count

    Command peekCommand();                      // Peek at next command

    boolean isQueueEmpty();                     // Test is queue is empty

    void cancelQueuedCommands();                // Clear command queue


    // State
    void powerDown();                           // Set power down state

    void powerUp();                             // Set power up state

    void combatState();                         // Set combat state on entity

    void standby();                             // Set standby state on entity

    // Decommission
    void decommissionEntity();                  // Destroy entity

    // Required Accessors
    int getOwnerID();                           // Get owning player id

    Location getLocation();                     // Get location of entity

    void setLocation(Location location);        // Set location

    PowerState getPowerState();                 // Get power state

    void setPowerState(PowerState state);       // Set power state

    boolean canMake();

    boolean canHeal();

    boolean canAttack();

    boolean canDefend();

    boolean canPowerUp();

    boolean canPowerDown();

    boolean canCancelCommandQueue();

    boolean canDecomission();

    boolean canMove();

}
