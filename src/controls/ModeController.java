package src.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by gavin on 2/1/17.
 */
public class ModeController {
    private ModeEnum mode;
    private TypeController typeController;
    private final static Logger log = LogManager.getLogger(ModeController.class);

    public ModeController() {
        log.debug("Mode controller instantiated");
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
