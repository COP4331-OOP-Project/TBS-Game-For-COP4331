package src.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EventController implements KeyListener{

	Game game;

	public EventController(Game game) {
		this.game = game;
	}

	private void controlDownActions(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				this.game.cycleModeForward();
				break;
			case KeyEvent.VK_DOWN:
				this.game.cycleModeBackward();
				break;
			case KeyEvent.VK_RIGHT:
				this.game.cycleTypeForward();
				break;
			case KeyEvent.VK_LEFT:
				this.game.cycleTypeBackward();
				break;
			default:
				System.out.println("Invalid control");
				break;
		}
	}

	private void normalKeyPressActions(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				this.game.cycleCommandForward();
				break;
			case KeyEvent.VK_DOWN:
				this.game.cycleCommandBackward();
				break;
			case KeyEvent.VK_LEFT:
				this.game.cycleTypeInstanceBackward();
				break;
			case KeyEvent.VK_RIGHT:
				this.game.cycleTypeInstanceForward();
				break;
			default:
				System.out.println("Invalid control");
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