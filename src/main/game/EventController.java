package game;

import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EventController implements KeyListener{

	Game game;
	private final static Logger log = LogManager.getLogger(EventController.class);
	private boolean gettingMoves = false;
	private boolean gettingMakeList = false;

	public EventController(Game game) {
		this.game = game;
	}

	private void controlDownActions(KeyEvent e) {
		int key = e.getKeyCode();
		game.setShowingMakeDetails(false);
		switch(key) {
			case KeyEvent.VK_UP:
				log.debug("CTRL + Up key pressed");
				this.game.cycleModeBackward();
				break;
			case KeyEvent.VK_DOWN:
				log.debug("CTRL + Down key pressed");
				this.game.cycleModeForward();
				break;
			case KeyEvent.VK_RIGHT:
				log.debug("CTRL + Right key pressed");
				this.game.cycleTypeForward();
				break;
			case KeyEvent.VK_LEFT:
				log.debug("CTRL + Left key pressed");
				this.game.cycleTypeBackward();
				break;
			default:
				log.info("Invalid command");
				break;
		}
	}

	private void normalKeyPressActions(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				log.debug("Up key pressed");
				this.game.cycleCommandForward();
				break;
			case KeyEvent.VK_DOWN:
				log.debug("Down key pressed");
				this.game.cycleCommandBackward();
				break;
			case KeyEvent.VK_LEFT:
				log.debug("Left key pressed");
				this.game.cycleTypeInstanceBackward();
				break;
			case KeyEvent.VK_RIGHT:
				log.debug("Right key pressed");
				this.game.cycleTypeInstanceForward();
				break;
			case KeyEvent.VK_ENTER:
				log.debug("Enter pressed");
				CommandEnum command = this.game.executeCommand();
				if (command == CommandEnum.MOVE) {
					this.gettingMoves = true;
				} else if (command == CommandEnum.MAKE) {
					this.gettingMakeList = true;
				}
				break;
            case KeyEvent.VK_NUMPAD5:
                log.debug("Numpad 5 pressed");
                this.game.centerOnCurrentTypeInstance();
                break;
			case KeyEvent.VK_ESCAPE:
				log.debug("Escape pressed");
				this.game.endTurn();
				break;
			case KeyEvent.VK_Z:
				log.debug("Z Pressed");
				this.game.toggleUnitOverview();
				break;
			case KeyEvent.VK_X:
				log.debug("X Pressed");
				this.game.toggleStructureOverview();
				break;
            case KeyEvent.VK_F:
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
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_NUMPAD1:
				log.debug("Numpad 1 pressed");
				this.game.addMoveToList(225);
				break;
			case KeyEvent.VK_NUMPAD3:
				log.debug("Numpad 3 pressed");
				this.game.addMoveToList(135);
				break;
			case KeyEvent.VK_NUMPAD4:
				log.debug("Numpad 4 pressed");
				this.game.addMoveToList(270);
				break;
			case KeyEvent.VK_NUMPAD5:
				log.debug("Numpad 5 pressed");
				this.game.centerOnLastMoveLocation();
				break;
			case KeyEvent.VK_NUMPAD6:
				log.debug("Numpad 6 pressed");
				this.game.addMoveToList(90);
				break;
			case KeyEvent.VK_NUMPAD7:
				log.debug("Numpad 7 pressed");
				this.game.addMoveToList(315);
				break;
			case KeyEvent.VK_NUMPAD9:
				log.debug("Numpad 9 pressed");
				this.game.addMoveToList(45);
				break;
			case KeyEvent.VK_ENTER:
				log.debug("Enter pressed");
				game.executeMoveCommand();
				this.gettingMoves = false;
				break;
		}
	}

	// Handle cycle through make list
	public void getMakeLIst(KeyEvent e) {
		int key = e.getKeyCode();

		// Cycle key mode choices for
		switch (key) {
			case KeyEvent.VK_UP:
				log.debug("UP Key pressed (Make)");
				this.game.cycleMakeOptionUp();
				break;
			case KeyEvent.VK_DOWN:
				log.debug("DOWN Key pressed (Make)");
				this.game.cycleMakeOptionDown();
				break;
			case KeyEvent.VK_ENTER:
				log.debug("Enter pressed");
				if (game.getCurrentType() == UnitEnum.COLONIST) game.executeMakeCommand(UnitEnum.COLONIST, "base");
				else {
					switch (this.game.getCurrentMakeOption()) {
						case 0: game.executeMakeCommand(StructureEnum.BASE, "melee"); break;
						case 1: game.executeMakeCommand(StructureEnum.BASE, "ranged"); break;
						case 2: game.executeMakeCommand(StructureEnum.BASE, "colonist"); break;
						case 3: game.executeMakeCommand(StructureEnum.BASE, "explorer"); break;
					}

				}
				this.gettingMakeList = false;
				this.game.setShowingMakeDetails(false);
				break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (this.gettingMoves) {
			this.getMoves(e);
		}

		else if (this.gettingMakeList) {
			this.getMakeLIst(e);
		}

		else if (e.isControlDown()) {
			controlDownActions(e);
			return;
		}

		else normalKeyPressActions(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}