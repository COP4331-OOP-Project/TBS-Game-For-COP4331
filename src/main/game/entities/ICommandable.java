package game.entities;

import game.commands.Command;

/**
 * Interface for basic commands PowerUp, PowerDown, Decommission,
 */
public interface ICommandable {

    void addCommandToQueue(Command command);    // Add new command to queue
    void doTurn();                              // Iterate turn
    Command nextCommand();                      // Next queue for new command or decrement turn count
    Command peekCommand();                      // Peek at next command
    void cancelQueuedCommands();                // Clear command queue
    void powerDown();                           // Set power down state
    void powerUp();                             // Set power up state
    void decommissionEntity();                  // Destroy entity
    boolean isQueueEmpty();                     // Test is queue is empty
    void combatState();                         // Set combat state on entity
    void standby();                        // Set standby state on entity

}
