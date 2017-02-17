package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
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

	public void mouseDragStart(MouseEvent event) {
		game.setIsDragging(true);
	}

	public void mouseDragStop(MouseEvent event) {
		game.setIsDragging(false);
	}
	
	public void handleEvents() {
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragged(event);
            }
        });
    	
    	scene.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragStart(event);
            }
        });
    	
    	scene.setOnMouseDragExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragStop(event);
            }
        });
    	
    	scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
               mouseScrolled(event);
            }
        });
    	
    	scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicked(event);
            }
        });
	}
}
