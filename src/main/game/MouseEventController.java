package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MouseEventController {
	Game game;
	Scene scene;
	public MouseEventController(Game game, Scene scene) {
		this.game = game;
		this.scene = scene;
	}

	public void mouseDragged(MouseEvent event) {
		game.updateViewLocation(event.getX(),
				event.getY());
	}

	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void mouseScrolled(ScrollEvent event) {
		
	}
}
