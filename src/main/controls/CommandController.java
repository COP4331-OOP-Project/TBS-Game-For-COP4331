package controls;

import game.commands.Command;
import game.entities.ICommandable;

/**
 * Created by gavin on 2/5/17.
 */
public class CommandController {

    private Command currentCommand;
    private ICommandable commandable;

    public CommandController(ICommandable commandable) {
        this.commandable = commandable;
    }

    public Command getCurrentCommand() {
        return this.currentCommand;
    }

    public void cycleForward() {

    }

    public void cycleBackward() {

    }
}
