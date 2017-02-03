package controls.Army;

import controls.Type;

/**
 * Created by gavin on 2/2/17.
 */
public class ArmyType implements Type {


    private ArmyEnum currentArmyType;

    public ArmyType() {
        this.currentArmyType = ArmyEnum.ENTIRE_ARMY;
    }

    public ArmyEnum getType() {
        return this.currentArmyType;
    }

    public ArmyEnum cycleForward() {
        this.currentArmyType = currentArmyType.getNext();
        return this.currentArmyType;
    }

    public ArmyEnum cycleBackward() {
        this.currentArmyType = currentArmyType.getPrevious();
        return this.currentArmyType;
    }
}

