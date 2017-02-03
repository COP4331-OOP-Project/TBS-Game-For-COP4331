import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventController implements KeyListener{
	
	Game game;
	
	public EventController(Game game) {
		this.game = game;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/* Here's a sample key press
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			game.someGameFunction();
		}
		
		if (key == KeyEvent.VK_DOWN) {
			game.someOtherGameFunction();
		}
		*/
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}