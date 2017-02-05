package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EventController implements KeyListener{

	Game game;
	private final static Logger log = LogManager.getLogger(EventController.class);

	public EventController(Game game) {
		this.game = game;
	}

	private void controlDownActions(KeyEvent e) {
		int key = e.getKeyCode();
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
			case KeyEvent.VK_NUMPAD1:
				log.debug("Numpad 1 pressed");
				break;
			case KeyEvent.VK_NUMPAD2:
				log.debug("Numpad 2 pressed");
				break;
			case KeyEvent.VK_NUMPAD3:
				log.debug("Numpad 3 pressed");
				break;
			case KeyEvent.VK_NUMPAD4:
				log.debug("Numpad 4 pressed");
				break;
			case KeyEvent.VK_NUMPAD5:
				log.debug("Numpad 5 pressed");
				break;
			case KeyEvent.VK_NUMPAD6:
				log.debug("Numpad 6 pressed");
				break;
			case KeyEvent.VK_NUMPAD7:
				log.debug("Numpad 7 pressed");
				break;
			case KeyEvent.VK_NUMPAD8:
				log.debug("Numpad 8 pressed");
				break;
			case KeyEvent.VK_NUMPAD9:
				log.debug("Numpad 9 pressed");
				break;
			case KeyEvent.VK_ENTER:
				log.debug("Enter pressed");
				break;
			case KeyEvent.VK_ESCAPE:
				log.debug("Escape pressed");
				break;
			default:
				log.info("Invalid command");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.isControlDown()) {
			controlDownActions(e);
			return;
		}

		normalKeyPressActions(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}