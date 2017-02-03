package controls.Unit;

/**
 * Created by gavin on 2/2/17.
 */
public enum UnitEnum {
    EXPLORER, COLONIST, MELEE, RANGED;

    public UnitEnum getNext() {
        return this.ordinal() < UnitEnum.values().length - 1
                ? UnitEnum.values()[this.ordinal() + 1]
                : UnitEnum.values()[0];
    }

    public UnitEnum getPrevious() {
        return this.ordinal() > 0
                ? UnitEnum.values()[this.ordinal() - 1]
                : UnitEnum.values()[UnitEnum.values().length-1];
    }
}
