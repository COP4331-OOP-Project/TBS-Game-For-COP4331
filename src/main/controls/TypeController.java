package controls;


import controls.army.ArmyEnum;
import controls.army.ArmyType;
import controls.rallyPoint.RallyPointEnum;
import controls.rallyPoint.RallyPointType;
import controls.structure.StructureEnum;
import controls.structure.StructureType;
import controls.typeInstance.TypeInstanceController;
import controls.unit.UnitEnum;
import controls.unit.UnitType;
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
    private Player player;
    private final static Logger log = LogManager.getLogger(ModeController.class);


    public TypeController(ModeEnum mode, Player p) {
        this.mode = mode;
        this.player = p;
        this.setMode(this.mode);
        // TODO: change start type to actual
        this.typeInstanceController = new TypeInstanceController(p, UnitEnum.MELEE);
        switch(mode) {
            case STRUCTURE:
                this.typeInstanceController = new TypeInstanceController(p, StructureEnum.BASE);
                break;
            case UNIT:
                this.typeInstanceController = new TypeInstanceController(p, UnitEnum.EXPLORER);
                break;
            case ARMY:
                this.typeInstanceController = new TypeInstanceController(p, ArmyEnum.ENTIRE_ARMY);
                break;
            case RALLY_POINT:
                this.typeInstanceController = new TypeInstanceController(p, RallyPointEnum.RALLY_POINT);
                break;
            default:
                throw new RuntimeException("Cannot instantiate TypeController with mode: " + mode);
        }
    }

    public Enum getType() {
        if (this.currentType == null) return null;
        return this.currentType.getType();
    }

    public TypeInstanceController getTypeInstanceController() {
        return this.typeInstanceController;
    }

    protected void setMode(ModeEnum mode) {
        switch(mode) {
            case STRUCTURE:
                this.currentType = new StructureType();
                this.typeInstanceController = new TypeInstanceController(this.player, StructureEnum.BASE);
                break;
            case UNIT:
                this.currentType = new UnitType();
                this.typeInstanceController = new TypeInstanceController(this.player, UnitEnum.EXPLORER);
                break;
            case ARMY:
                this.currentType = new ArmyType();
                this.typeInstanceController = new TypeInstanceController(this.player, ArmyEnum.ENTIRE_ARMY);
                break;
            case RALLY_POINT:
                this.currentType = new RallyPointType();
                this.typeInstanceController = new TypeInstanceController(this.player, RallyPointEnum.RALLY_POINT);
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
        this.typeInstanceController = new TypeInstanceController(this.player, this.currentType.getType());
        return this.getType();
    }

    public Enum cycleBackward() {
        if (this.currentType == null) {
            log.error("Current type cannot be cycled because it is null. Mode is set to: " + this.mode);
            return null;
        }
        this.currentType.cycleBackward();
        this.typeInstanceController = new TypeInstanceController(this.player, this.currentType.getType());
        return this.getType();
    }


}
