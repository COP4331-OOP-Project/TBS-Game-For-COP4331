package controls;

/**
 * Created by gavin on 2/2/17.
 */
public enum ModeEnum {
    RALLY_POINT, STRUCTURE, UNIT, ARMY;

    public ModeEnum getNext() {
        return this.ordinal() < ModeEnum.values().length - 1
                ? ModeEnum.values()[this.ordinal() + 1]
                : ModeEnum.values()[0];
    }

    public ModeEnum getPrevious() {
        return this.ordinal() > 0
                ? ModeEnum.values()[this.ordinal() - 1]
                : ModeEnum.values()[ModeEnum.values().length-1];
    }
}
