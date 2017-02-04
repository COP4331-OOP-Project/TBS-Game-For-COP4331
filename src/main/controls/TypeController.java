package controls;


import controls.Army.ArmyType;
import controls.Structure.StructureType;
import controls.TypeInstance.TypeInstanceController;
import controls.Unit.UnitEnum;
import controls.Unit.UnitType;
import game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by gavin on 2/1/17.
 */
public class TypeController {

    // TODO: save progress in each category
    private Type currentType;
    private ModeEnum mode;
    private TypeInstanceController typeInstanceController;
    private final static Logger log = LogManager.getLogger(ModeController.class);


    public TypeController(ModeEnum mode, Player p) {
        this.mode = mode;
        this.setMode(this.mode);
        // TODO: change start type to actual
        this.typeInstanceController = new TypeInstanceController(p, UnitEnum.MELEE);
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
                this.currentType = null;
                break;
            default:
                throw new RuntimeException("Cannot switch to mode: " + mode);
        }
    }

    public Enum cycleForward() {
        if (this.currentType == null) {
            log.error("Current type cannot be cycled because it is null. Mode is set to: " + this.mode);
            return null;
        }
        this.currentType.cycleForward();
        return this.getType();
    }

    public Enum cycleBackward() {
        if (this.currentType == null) {
            log.error("Current type cannot be cycled because it is null. Mode is set to: " + this.mode);
            return null;
        }
        this.currentType.cycleBackward();
        return this.getType();
    }


}
