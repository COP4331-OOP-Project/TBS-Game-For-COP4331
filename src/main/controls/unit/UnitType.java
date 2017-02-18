package controls.unit;

import controls.Type;

/**
 * Created by gavin on 2/2/17.
 */
public class UnitType implements Type {


    private UnitEnum currentUnitType;

    public UnitType() {
        this.currentUnitType = UnitEnum.EXPLORER;
    }

    public UnitEnum getType() {
        return this.currentUnitType;
    }

    public UnitEnum cycleForward() {
        this.currentUnitType = currentUnitType.getNext();
        return this.currentUnitType;
    }

    public UnitEnum cycleBackward() {
        this.currentUnitType = currentUnitType.getPrevious();
        return this.currentUnitType;
    }
}
