package controls.command;

import game.entities.ICommandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by gavin on 2/5/17.
 */
public class CommandController {

    private final static Logger log = LogManager.getLogger(CommandController.class);
    private CommandEnum currentCommand;
    private ICommandable commandable;

    public CommandController(ICommandable commandable) {
        this.commandable = commandable;
        this.currentCommand = CommandEnum.MAKE;
    }

    public CommandEnum getCurrentCommand() {
        return this.currentCommand;
    }

    private boolean canExecuteCommand(CommandEnum command) {
        switch(command) {
            case MAKE:
                return this.commandable.canMake();
            case HEAL:
                return this.commandable.canHeal();
            case ATTACK:
                return this.commandable.canAttack();
            case DEFEND:
                return this.commandable.canDefend();
            case POWER_UP:
                return this.commandable.canPowerUp();
            case POWER_DOWN:
                return this.commandable.canPowerDown();
            case CANCEL_COMMAND_QUEUE:
                return this.commandable.canCancelCommandQueue();
            case DECOMISSION:
                return this.commandable.canDecomission();
            case MOVE:
                return this.commandable.canMove();
            default:
                log.error("Tried to execute invalid command: {} ", command);
                return false;
        }
    }

    public CommandEnum cycleForward() {
        CommandEnum command = this.currentCommand.getNext();
        if (canExecuteCommand(command)) {
            this.currentCommand = command;
            return command;
        }

        int counter = 0;
        while (!canExecuteCommand(command) && counter < 9) {
            command = command.getNext();
            counter++;
        }
        this.currentCommand = command;
        return command;
    }

    public CommandEnum cycleBackward() {
        CommandEnum command = this.currentCommand.getPrevious();
        if (canExecuteCommand(command)) {
            this.currentCommand = command;
            return command;
        }

        int counter = 0;
        while (!canExecuteCommand(command) && counter < 9) {
            command = command.getPrevious();
            counter++;
        }
        this.currentCommand = command;
        return command;
    }
}
