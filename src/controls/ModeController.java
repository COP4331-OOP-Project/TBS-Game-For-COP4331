package controls;

import org.apache.log4j.Logger;
/**
 * Created by gavin on 2/1/17.
 */
public class ModeController {
    private ModeEnum mode;
    private TypeController typeController;
    static Logger log = Logger.getLogger(ModeController.class.getName());

    public ModeController() {
        this.mode = ModeEnum.RALLY_POINT;
        this.typeController = new TypeController(this.mode);
    }

    public ModeEnum getMode() {
        return this.mode;
    }

    public TypeController getTypeController() {
        return this.typeController;
    }

    public ModeEnum cycleForward() {
        this.mode = this.mode.getNext();
        this.typeController.setMode(this.mode);
        return this.mode;
    }

    public ModeEnum cycleBackward() {
        this.mode = this.mode.getPrevious();
        this.typeController.setMode(this.mode);
        return this.mode;
    }
}
