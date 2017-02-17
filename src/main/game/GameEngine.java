package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.View;

public class GameEngine extends Application {
    Game game = new Game();
    private int defaultScreenWidth = 1366;
    private int defaultScreenHeight = 768;
    private EventController events;
    private View view;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        Group root = new Group();
        Canvas canvas = new Canvas(defaultScreenWidth, defaultScreenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        view = new View(game, gc);

        Scene scene = new Scene(root, Color.BLACK);
        events = new EventController(game, scene);

        sendEventsToController(scene);

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

    public void sendEventsToController(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                events.keyPressed(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                events.keyReleased(event);
            }
        });

        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                events.keyTyped(event);
            }
        });
    }
}