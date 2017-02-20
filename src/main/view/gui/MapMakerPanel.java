package view.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Point;
import view.View;
import view.game.Camera;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import game.Assets;
import game.gameboard.MapLoader;

public class MapMakerPanel extends Panel{
    private static final int BOARD_SIZE = 42;
    public static int GUI_PANEL_WIDTH =
            (int) Assets.getInstance().getImage("GUI_TOP").getWidth();
    Point screenDimensions = new Point(0,0);
    Point offset = new Point(20, -600);
    Camera camera = new Camera(screenDimensions);
	MapLoader mapLoader = new MapLoader();
	View view;
	File waterMap = new File("assets/maps/allwater.map");
	
	Group root; //Any GUI Elements Must Be Added and Removed From Here
	StackPane mapStuff = new StackPane();
	Button loadMapButton = new Button("Load Map");
	Button saveMapButton = new Button("Save Map");
	Button exitToMenuButton = new Button("Exit To Main Menu");
	
	int currentDrawingType = 0;
	int[][] map;
	
	public MapMakerPanel(Group root, View view) {
		this.view = view;
		this.root = root;
		camera.setScale(0.3);
		camera.setOffset(offset);
		map = mapLoader.getMap(BOARD_SIZE, waterMap);
		setUpButtons();
	}
	
	private void setUpButtons() {
		loadMapButton.setTranslateX(300);
		loadMapButton.setTranslateY(8);
		loadMapButton.setId("button");
		loadMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMap();
            }
        });
		saveMapButton.setTranslateX(450);
		saveMapButton.setTranslateY(8);
		saveMapButton.setId("button");
		saveMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveMap();
            }
        });
		exitToMenuButton.setTranslateY(8);
		exitToMenuButton.setId("button");
		exitToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.mainMenuMode();
            }
        });
		mapStuff.getChildren().add(loadMapButton);
		mapStuff.getChildren().add(saveMapButton);
		mapStuff.getChildren().add(exitToMenuButton);
		loadMapButton.setVisible(true);
		saveMapButton.setVisible(true);
		exitToMenuButton.setVisible(true);
	}

	@Override
	public void draw(GraphicsContext gc, Point screenDimensions) {
		exitToMenuButton.setTranslateX(screenDimensions.x - 230);
		this.screenDimensions.x = screenDimensions.x;
		this.screenDimensions.y = screenDimensions.y;
		this.offset.x = screenDimensions.x/2 -620;
		this.offset.y = screenDimensions.y/2 - 1015;
		  for (int i = 0; i < map.length; i++) {
	            for (int j = 0; j < map[i].length; j++) {
	            	drawTile(gc, new Point(i,j), map[i][j]);
	            }
		  }
		  drawTopBar(gc);
		 
	}

	private void drawTopBar(GraphicsContext gc) {
	      gc.drawImage(Assets.getInstance().getImage("GUI_TOP_LEFT"), 0, 0);
	      gc.drawImage(Assets.getInstance().getImage("GUI_TOP_RIGHT"),
	      screenDimensions.x - GUI_PANEL_WIDTH, 0);
	      int distanceFromRight = screenDimensions.x - GUI_PANEL_WIDTH;
	      for (int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
	            gc.drawImage(Assets.getInstance().getImage("GUI_TOP_MIDDLE"), i, 0);
	      }
	      drawCurrentTile(gc);
	      gc.setFont(Assets.getInstance().getFont(2));
	      gc.fillText("Map Maker", 12, 37);
	}

	private void drawCurrentTile(GraphicsContext gc) {
	    Image img;
		switch (currentDrawingType) {
	    	case 0:
	    		img = Assets.getInstance().getImage("TERRAIN_GRASS1");
	        	gc.drawImage(img, 210, 0, img.getWidth()/2.5, 
	            		img.getHeight()/2.5);
	            break;
	        case 1:
	    		img = Assets.getInstance().getImage("TERRAIN_SAND");
	    		gc.drawImage(img, 210, 0, img.getWidth()/2.5, 
	            		img.getHeight()/2.5);
	            break;
	        case 2:
	        	img = Assets.getInstance().getImage("TERRAIN_WATER1");
	        	gc.drawImage(img, 210, 0, img.getWidth()/2.5, 
	            		img.getHeight()/2.5);
	            break;
	        case 3:
	        	img = Assets.getInstance().getImage("TERRAIN_MOUNTAIN1");
	        	gc.drawImage(img, 210, 0, img.getWidth()/2.5, 
	            		img.getHeight()/2.5);
	            break;
	        case -1:
	            break;
	    }
		
	}

	protected void drawTile(GraphicsContext gc, Point p, int type) {
	    Image img;
		switch (type) {
	    	case 0:
	    		img = Assets.getInstance().getImage("TERRAIN_GRASS1");
	        	gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
	            		camera.getScale() * img.getHeight());
	            break;
	        case 1:
	    		img = Assets.getInstance().getImage("TERRAIN_SAND");
	    		gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
	            		camera.getScale() * img.getHeight());
	            break;
	        case 2:
	        	img = Assets.getInstance().getImage("TERRAIN_WATER1");
	        	gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
	            		camera.getScale() * img.getHeight());
	            break;
	        case 3:
	        	img = Assets.getInstance().getImage("TERRAIN_MOUNTAIN1");
	        	gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
	            		camera.getScale() * img.getHeight());
	            break;
	        case -1:
	            break;
	    }
	}

	public void tileClicked(Point point) {
		if (camera.getTileLocation(point).x < BOARD_SIZE &&
				camera.getTileLocation(point).y < BOARD_SIZE) {
			iterateTile(camera.getTileLocation(point));
		}
	}

	private void iterateTile(Point tileLocation) {
		if (map[tileLocation.x][tileLocation.y] != -1) {
			map[tileLocation.x][tileLocation.y] = currentDrawingType;
		}
	}

	public void changePaintColor() {
		currentDrawingType = (currentDrawingType + 1) % 4;
	}

	public void saveMap() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Map");
		fileChooser.setInitialDirectory(new File("assets/maps"));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Map Files", "*.map"));
		File saveMap = fileChooser.showSaveDialog(null);
		if (saveMap != null) {
			saveFile(saveMap);
		}
	}

	private void saveFile(File saveMap) {  
		BufferedWriter writeMap;
		try {
			writeMap = new BufferedWriter(new PrintWriter(saveMap));
			for (int i = 0; i < map.length; i++) {
				   String s = "";
				   for (int j = 0; j < map[i].length; j++) {
					   if (map[i][j] != -1) {
						   s += map[i][j] + " ";
					   }
				   }
				   writeMap.write(s);
				   writeMap.newLine();
			   }
			writeMap.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		   
	}

	public void loadMap() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Map");
		fileChooser.setInitialDirectory(new File("assets/maps"));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Map Files", "*.map"));
		File newMap = fileChooser.showOpenDialog(null);
		if (newMap != null) {
			map = mapLoader.getMap(BOARD_SIZE, newMap);
		}
	}

	@Override
	public void hideGUIElements() {
		root.getChildren().remove(mapStuff);
	}

	@Override
	public void showGUIElements() {
		root.getChildren().add(mapStuff);
	}
}