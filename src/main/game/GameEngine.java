package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.View;

public class GameEngine extends Application {
    Game game = new Game();
    private int defaultScreenWidth = 1366;
    private int defaultScreenHeight = 768;
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
    private View view;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        Group root = new Group();
        Canvas canvas = new Canvas(defaultScreenWidth, defaultScreenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        view = new View(game, gc);

        Scene scene = new Scene(root, Color.BLACK);
    	keyEvents = new KeyEventController(game, scene);
    	mouseEvents = new MouseEventController(game, scene);

    	keyEvents.handleEvents();
    	mouseEvents.handleEvents();
    	
        //This is new game loop using JavaFX timer.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                canvas.setWidth(scene.getWidth());
                canvas.setHeight(scene.getHeight());
                game.updateGame();
                view.renderGame((int) canvas.getWidth(), (int) canvas.getHeight());
            }
        };
        timer.start();
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 
}