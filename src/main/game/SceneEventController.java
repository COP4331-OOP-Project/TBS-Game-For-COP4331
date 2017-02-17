package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class SceneEventController {
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
    Game game;
    Scene scene;
    
    public SceneEventController(Game game, Scene scene) {
    	this.game = game;
    	this.scene = scene;
    	keyEvents = new KeyEventController(game);
    	mouseEvents = new MouseEventController(game);
    }
    
    protected void handleEvents() {
	        //Mouse Events
	    	scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                mouseEvents.mouseDragged(event);
	            }
	        });
	    	
	    	scene.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                mouseEvents.mouseDragStart(event);
	            }
	        });
	    	
	    	scene.setOnMouseDragExited(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                mouseEvents.mouseDragStop(event);
	            }
	        });
	    	
	    	scene.setOnScroll(new EventHandler<ScrollEvent>() {
	            @Override
	            public void handle(ScrollEvent event) {
	                mouseEvents.mouseScrolled(event);
	            }
	        });
	    	
	    	scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                mouseEvents.mouseClicked(event);
	            }
	        });
	    	

	    	
	    	//Key Events
	    	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                keyEvents.keyPressed(event);
	            }
	        });

	        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                keyEvents.keyReleased(event);
	            }
	        });

	        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                keyEvents.keyTyped(event);
	            }
	        });
    }
}
