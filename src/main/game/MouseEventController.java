package game;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MouseEventController {
	Game game;
	Scene scene;
	
	private boolean isDragging;
	
	public MouseEventController(Game game) {
		this.game = game;
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

	public void mouseDragStart(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragStop(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
}
