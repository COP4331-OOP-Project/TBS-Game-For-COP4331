package controls;

import game.commands.Command;
import game.entities.ICommandable;

/**
 * Created by gavin on 2/5/17.
 */
public class CommandController {

    private Command currentCommand;
    private ICommandable commandable;

    CommandController(ICommandable commandable) {
        this.commandable = commandable;
    }
}
