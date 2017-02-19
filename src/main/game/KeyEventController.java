package game;

import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.View;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyEventController {

    private final static Logger log = LogManager.getLogger(KeyEventController.class);
    Game game;
    View view;
    Scene scene;
    ControlFileReader controlReader;
    private boolean gettingMoves = false;
    private boolean gettingMakeList = false;

    public KeyEventController(Game game, View view, Scene scene) {
    	controlReader = new ControlFileReader();
        this.game = game;
        this.view = view;
        this.scene = scene;
    }

    private void controlDownActions(KeyEvent e) {
        KeyCode key = e.getCode();
        game.setShowingMakeDetails(false);
        if (key == controlReader.getControl("COMMAND_UP")) {
                log.debug("CTRL + Up key pressed");
                this.game.cycleModeBackward();
                
        } else if (key == controlReader.getControl("COMMAND_DOWN")) {
            	log.debug("CTRL + Down key pressed");
            	this.game.cycleModeForward();
            	
        } else if (key == controlReader.getControl("COMMAND_LEFT")) {
                log.debug("CTRL + Right key pressed");
                this.game.cycleTypeForward();
                
        } else if (key == controlReader.getControl("COMMAND_RIGHT")) {
                log.debug("CTRL + Left key pressed");
                this.game.cycleTypeBackward();
                
        } else {
                log.info("Invalid command");
        }
    }

    private void normalKeyPressActions(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == controlReader.getControl("COMMAND_UP")) {
        	log.debug("Up key pressed");
            this.game.cycleCommandForward();
            
        } else if (key == controlReader.getControl("COMMAND_DOWN")) {
        	log.debug("Down key pressed");
        	this.game.cycleCommandBackward();
        	
        } else if (key == controlReader.getControl("COMMAND_LEFT")) {
        	log.debug("Right key pressed");
            this.game.cycleTypeInstanceBackward();
            
        } else if (key == controlReader.getControl("COMMAND_RIGHT")) {
        	log.debug("Left key pressed");
            this.game.cycleTypeInstanceForward();
            
        } else if (key == controlReader.getControl("SELECT_ITEM")) {
            log.debug("Select key pressed");
            CommandEnum command = this.game.executeCommand();
            if (command == CommandEnum.MOVE) {
                this.gettingMoves = true;
            } else if (command == CommandEnum.MAKE) {
                this.gettingMakeList = true;
            }
        } else if (key == controlReader.getControl("CENTER")) {
        	log.debug("Center key pressed");
            this.game.centerOnCurrentTypeInstance();

        } else if (key == controlReader.getControl("END_TURN")) {
            log.debug("Escape pressed");
            this.game.endTurn();
            
        } else if (key == controlReader.getControl("UNIT_OVERVIEW")) {
            log.debug("Unit Overview Pressed");
            this.view.toggleUnitOverview();
            
        } else if (key == controlReader.getControl("STRUCTURE_OVERVIEW")) {
            log.debug("Structure Overview Pressed");
            this.view.toggleStructureOverview();
            
        } else if (key == controlReader.getControl("FORM_ARMY")) {
        	log.debug("Form Army Key Pressed");
            this.game.formArmy();
            this.game.resetControls();
            
        } else {
            log.info("Invalid command");
        }
    }

    public void getMoves(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == controlReader.getControl("MOVE_0")) {
            log.debug("Move 0 Key Pressed");
            this.game.addMoveToList(0);
            
        } else if (key == controlReader.getControl("MOVE_45")) {
        	log.debug("Move 45 Key Pressed");
            this.game.addMoveToList(45);
        	
        } else if (key == controlReader.getControl("MOVE_135")) {
        	log.debug("Move 135 Key Pressed");
            this.game.addMoveToList(135);
        	
        } else if (key == controlReader.getControl("MOVE_180")) {
        	log.debug("Move 180 Key Pressed");
            this.game.addMoveToList(180);
        	
        } else if (key == controlReader.getControl("MOVE_225")) {
        	log.debug("Move 225 Key Pressed");
            this.game.addMoveToList(225);
        	
        } else if (key == controlReader.getControl("MOVE_315")) {
        	log.debug("Move 315 Key Pressed");
            this.game.addMoveToList(315);
        } else if (key == controlReader.getControl("SELECT_ITEM")) {
            log.debug("Select Item Pressed");
            game.executeMoveCommand();
            this.gettingMoves = false;
        } else {
            log.info("Invalid command");
        }
    }

    // Handle cycle through make list
    public void getMakeList(KeyEvent e) {
        KeyCode key = e.getCode();

        // Cycle key mode choices for
        if (key == controlReader.getControl("COMMAND_UP")) {
        	log.debug("UP Key pressed (Make)");
            this.game.cycleMakeOptionUp();
            
        } else if (key == controlReader.getControl("COMMAND_DOWN")) {
        	log.debug("DOWN Key pressed (Make)");
            this.game.cycleMakeOptionDown();
        	
        } else if (key == controlReader.getControl("SELECT_ITEM")) {
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
        }
    }

    public void keyReleased(KeyEvent e) {
        if (this.gettingMoves) {
            this.getMoves(e);
        } else if (this.gettingMakeList) {
            this.getMakeList(e);
        } else if ((e.isControlDown() && (controlReader.getControl("CHANGE_CYCLING") == KeyCode.CONTROL)) ||
        			(e.isAltDown() && (controlReader.getControl("CHANGE_CYCLING") == KeyCode.ALT)) ||
        			(e.isShiftDown() && (controlReader.getControl("CHANGE_CYCLING") == KeyCode.SHIFT))){
            controlDownActions(e);
            return;
        } else normalKeyPressActions(e);
    }
    
    public void handleEvents() {
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyReleased(event);
            }
        });
    }

}