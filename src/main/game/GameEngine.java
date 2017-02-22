package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.View;

public class GameEngine extends Application {
    Game game = new Game();
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
    private View view;

    @Override
    public void start(Stage stage) {
        Assets.getInstance().loadResources();
        stage.setTitle("Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //primaryStage.setFullScreen(true);
        stage.setMaximized(true);
        view = new View(game, scene, root);
    	keyEvents = new KeyEventController(game, view, scene);
    	mouseEvents = new MouseEventController(game, view, scene);
    	keyEvents.handleEvents();
    	mouseEvents.handleEvents();
        //This is new game loop using JavaFX timer.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.updateGame();
                view.renderGame();
            }
        };
        timer.start();
        stage.setScene(scene);
        stage.show();
    }

 
}