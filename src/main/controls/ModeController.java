package controls;

import game.Player;

/**
 * Created by gavin on 2/1/17.
 */
public class ModeController {
    private ModeEnum mode;
    private TypeController typeController;

    public ModeController(Player p) {
        this.mode = ModeEnum.RALLY_POINT;
        this.typeController = new TypeController(this.mode, p);
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
