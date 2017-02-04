package controls.Army;

/**
 * Created by gavin on 2/2/17.
 */
public enum ArmyEnum {
    ENTIRE_ARMY, BATTLE_GROUP, REINFORCEMENTS;

    public ArmyEnum getNext() {
        return this.ordinal() < ArmyEnum.values().length - 1
                ? ArmyEnum.values()[this.ordinal() + 1]
                : ArmyEnum.values()[0];
    }

    public ArmyEnum getPrevious() {
        return this.ordinal() > 0
                ? ArmyEnum.values()[this.ordinal() - 1]
                : ArmyEnum.values()[ArmyEnum.values().length-1];
    }
}
