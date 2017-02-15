package view;
import java.awt.Dimension;
import java.awt.DisplayMode;

import javax.swing.JFrame;

import game.Assets;
import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Application {
	private static final boolean FULLSCREEN_MODE = false;
	static final long serialVersionUID = 1L;
	private View view;
	GraphicsContext gc;
	private int defaultScreenWidth = 1366;
	private int defaultScreenHeight = 768;
	private int screenWidth = defaultScreenWidth;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = defaultScreenHeight;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public int viewOffsetY = 0;
	public int viewOffsetX = 0;

	
	public void startGame(Game game) {
		Assets.getInstance().loadResources();
		view = new View(game);
	}
	
	public void renderGame() {
		view.drawVisiblePanels(gc, screenWidth, screenHeight);
		
	}

	public void updateAnimationTime() {
		view.updateAnimationTime();
	}
	
	public void show() {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Assets.getInstance().loadResources();
		Canvas canvas = new Canvas(screenWidth, screenHeight);
		canvas.setWidth(screenWidth);
		canvas.setHeight(screenHeight);
		gc = canvas.getGraphicsContext2D();
		Pane root = new Pane();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}