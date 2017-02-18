package controls.command;

/**
 * Created by gavin on 2/5/17.
 */
public enum CommandEnum {
    MAKE, HEAL, ATTACK, DEFEND, POWER_UP, POWER_DOWN, CANCEL_COMMAND_QUEUE, DECOMISSION, MOVE;

    public CommandEnum getNext() {
        return this.ordinal() < CommandEnum.values().length - 1
                ? CommandEnum.values()[this.ordinal() + 1]
                : CommandEnum.values()[0];
    }

    public CommandEnum getPrevious() {
        return this.ordinal() > 0
                ? CommandEnum.values()[this.ordinal() - 1]
                : CommandEnum.values()[CommandEnum.values().length - 1];
    }
}
