package src.controls;


import src.controls.Army.ArmyType;
import src.controls.Structure.StructureType;
import src.controls.Unit.UnitType;

/**
 * Created by gavin on 2/1/17.
 */
public class TypeController {

    // TODO: save progress in each category
    private Type currentType;

    public TypeController(ModeEnum mode) {
        this.setMode(mode);
    }

    public Enum getType() {
        if (this.currentType == null) return null;
        return this.currentType.getType();
    }

    protected void setMode(ModeEnum mode) {
        switch(mode) {
            case STRUCTURE:
                this.currentType = new StructureType();
                break;
            case UNIT:
                this.currentType = new UnitType();
                break;
            case ARMY:
                this.currentType = new ArmyType();
                break;
            case RALLY_POINT:
                // Do nothing
                break;
            default:
                throw new RuntimeException("Cannot switch to mode: " + mode);
        }
    }

    public Enum cycleForward(ModeEnum mode) {
        this.currentType.cycleForward();
        return this.getType();
    }

    public Enum cycleBackward(ModeEnum mode) {
        this.currentType.cycleBackward();
        return this.getType();
    }


}
