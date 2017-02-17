package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import view.View;

public class MouseEventController {
	Game game;
	View view;
	Scene scene;
	
	public MouseEventController(Game game, View view,  Scene scene) {
		this.game = game;
		this.view = view;
		this.scene = scene;
	}

	public void mouseDragged(MouseEvent event) {
		view.updateViewLocation(event.getX(),
				event.getY());
	}

	public void mouseClicked(MouseEvent event) {
		view.setStoppedDragging();
	}
	
	protected void mouseReleased(MouseEvent event) {
	}

	protected void mousePressed(MouseEvent event) {
		
		view.setDragging(event.getSceneX(), event.getSceneY());
	}

	public void mouseScrolled(ScrollEvent event) {
		
	}

	
	public void handleEvents() {
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragged(event);
            }
        });
    	
    	scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePressed(event);
            }
        });
    	
    	scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseReleased(event);
            }
        });
    	
    	scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicked(event);
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
