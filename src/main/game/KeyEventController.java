package game;

import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyEventController {

    private final static Logger log = LogManager.getLogger(KeyEventController.class);
    Game game;
    private boolean gettingMoves = false;
    private boolean gettingMakeList = false;

    public KeyEventController(Game game) {
        this.game = game;
    }

    private void controlDownActions(KeyEvent e) {
        KeyCode key = e.getCode();
        game.setShowingMakeDetails(false);
        switch (key) {
            case UP:
                log.debug("CTRL + Up key pressed");
                this.game.cycleModeBackward();
                break;
            case DOWN:
                log.debug("CTRL + Down key pressed");
                this.game.cycleModeForward();
                break;
            case RIGHT:
                log.debug("CTRL + Right key pressed");
                this.game.cycleTypeForward();
                break;
            case LEFT:
                log.debug("CTRL + Left key pressed");
                this.game.cycleTypeBackward();
                break;
            default:
                log.info("Invalid command");
                break;
        }
    }

    private void normalKeyPressActions(KeyEvent e) {
        KeyCode key = e.getCode();
        switch (key) {
            case UP:
                log.debug("Up key pressed");
                this.game.cycleCommandForward();
                break;
            case DOWN:
                log.debug("Down key pressed");
                this.game.cycleCommandBackward();
                break;
            case LEFT:
                log.debug("Left key pressed");
                this.game.cycleTypeInstanceBackward();
                break;
            case RIGHT:
                log.debug("Right key pressed");
                this.game.cycleTypeInstanceForward();
                break;
            case ENTER:
                log.debug("Enter pressed");
                CommandEnum command = this.game.executeCommand();
                if (command == CommandEnum.MOVE) {
                    this.gettingMoves = true;
                } else if (command == CommandEnum.MAKE) {
                    this.gettingMakeList = true;
                }
                break;
            case NUMPAD5:
                log.debug("Numpad 5 pressed");
                this.game.centerOnCurrentTypeInstance();
                break;
            case ESCAPE:
                log.debug("Escape pressed");
                this.game.endTurn();
                break;
            case Z:
                log.debug("Z Pressed");
                this.game.toggleUnitOverview();
                break;
            case X:
                log.debug("X Pressed");
                this.game.toggleStructureOverview();
                break;
            case F:
                log.debug("F pressed");
                this.game.formArmy();
                this.game.resetControls();
                break;
            default:
                log.info("Invalid command");
                break;
        }
    }

    public void getMoves(KeyEvent e) {
        KeyCode key = e.getCode();
        switch (key) {
            case NUMPAD1:
                log.debug("Numpad 1 pressed");
                this.game.addMoveToList(225);
                break;
            case NUMPAD2:
                log.debug("Numpad 2 pressed");
                this.game.addMoveToList(180);
                break;
            case NUMPAD3:
                log.debug("Numpad 3 pressed");
                this.game.addMoveToList(135);
                break;
            case NUMPAD5:
                log.debug("Numpad 5 pressed");
                this.game.centerOnLastMoveLocation();
                break;
            case NUMPAD7:
                log.debug("Numpad 7 pressed");
                this.game.addMoveToList(315);
                break;
            case NUMPAD8:
                log.debug("Numpad 8 pressed");
                this.game.addMoveToList(0);
                break;
            case NUMPAD9:
                log.debug("Numpad 9 pressed");
                this.game.addMoveToList(45);
                break;
            case ENTER:
                log.debug("Enter pressed");
                game.executeMoveCommand();
                this.gettingMoves = false;
                break;
            default:
                break;
        }
    }

    // Handle cycle through make list
    public void getMakeLIst(KeyEvent e) {
        KeyCode key = e.getCode();

        // Cycle key mode choices for
        switch (key) {
            case UP:
                log.debug("UP Key pressed (Make)");
                this.game.cycleMakeOptionUp();
                break;
            case DOWN:
                log.debug("DOWN Key pressed (Make)");
                this.game.cycleMakeOptionDown();
                break;
            case ENTER:
                log.debug("Enter pressed");
                if (game.getCurrentType() == UnitEnum.COLONIST) game.executeMakeCommand(UnitEnum.COLONIST, "base");
                else {
                    switch (this.game.getCurrentMakeOption()) {
                        case 0:
                            game.executeMakeCommand(StructureEnum.BASE, "melee");
                            break;
                        case 1:
                            game.executeMakeCommand(StructureEnum.BASE, "ranged");
                            break;
                        case 2:
                            game.executeMakeCommand(StructureEnum.BASE, "colonist");
                            break;
                        case 3:
                            game.executeMakeCommand(StructureEnum.BASE, "explorer");
                            break;
                    }

                }
                this.gettingMakeList = false;
                this.game.setShowingMakeDetails(false);
                break;
            default:
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (this.gettingMoves) {
            this.getMoves(e);
        } else if (this.gettingMakeList) {
            this.getMakeLIst(e);
        } else if (e.isControlDown()) {
            controlDownActions(e);
            return;
        } else normalKeyPressActions(e);
    }

    public void keyPressed(KeyEvent e) {
    }


    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}