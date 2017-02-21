package view;

import java.io.File;
import java.util.ArrayList;

import controls.ModeEnum;
import game.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import view.game.Camera;
import view.game.GamePanel;
import view.gui.*;
import view.gui.game.CivilizationPanel;
import view.gui.game.CommandPanel;
import view.gui.game.ControlModePanel;
import view.gui.game.MakeDetailsPanel;
import view.gui.game.MiniMapPanel;
import view.gui.game.StructureDetailsPanel;
import view.gui.game.StructureOverviewPanel;
import view.gui.game.UnitDetailsPanel;
import view.gui.game.UnitOverviewPanel;
import view.gui.screen.MainMenuPanel;
import view.gui.screen.MapMakerPanel;
import view.gui.screen.SettingsPanel;

public class View {
    private Game game;
    private Camera camera;
    
    //Game Panels
    private CivilizationPanel civPanel;
    private ControlModePanel modePanel;
    private GamePanel gamePanel;
    private StructureOverviewPanel structureOverviewPanel;
    private UnitOverviewPanel unitOverviewPanel;
    private StructureDetailsPanel structureDetailsPanel;
    private UnitDetailsPanel unitDetailsPanel;
    private MiniMapPanel miniMapPanel;
    private MakeDetailsPanel makePanel;
    private CommandPanel commandPanel;
    private MapMakerPanel mapMakerPanel;
    private MainMenuPanel mainMenuPanel;
    private SettingsPanel settingsPanel;
    private ArrayList<Panel> panels; //ArrayList Containing Panels
    private int defaultScreenWidth = 1366;
    private int defaultScreenHeight = 768; 
    private Canvas canvas; //The GraphicsContext Goes on here.
    private GraphicsContext gc; //Image drawing is done with this
    private Group root; //Gui drawing is added to this
    
    private Point screenDimensions;
    private ViewEnum viewMode;
    private Scene scene;
    
    private void startGameScreen() {
        //mainGameMode();
        //mapMakerMode();
        mainMenuMode();
	}
    
    public View(Game game, Scene scene, Group root) {
    	this.root = root;
    	canvas = new Canvas(defaultScreenWidth, defaultScreenHeight);
    	gc = canvas.getGraphicsContext2D();
    	this.scene = scene;
    	setScene();
        this.game = game;
        panels = new ArrayList<Panel>();
        screenDimensions = new Point();
        camera = new Camera(screenDimensions);    
        createPanels();
        panels.add(gamePanel);
        panels.add(civPanel);
        panels.add(modePanel);
        panels.add(structureOverviewPanel);
        panels.add(unitOverviewPanel);
        panels.add(unitDetailsPanel);
        panels.add(structureDetailsPanel);
        panels.add(miniMapPanel);
        panels.add(makePanel);
        panels.add(commandPanel);
        panels.add(mapMakerPanel);
        panels.add(mainMenuPanel);
        panels.add(settingsPanel);
        startGameScreen();
    }



	private void setScene() {
        root.getChildren().add(canvas);
        File buttonStyle = new File("assets/buttonStyle.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + buttonStyle.getAbsolutePath().replace("\\", "/"));;
	}

	private void createPanels() {
        civPanel = new CivilizationPanel(game);
        modePanel = new ControlModePanel(game);
        gamePanel = new GamePanel(game, camera);
        structureOverviewPanel = new StructureOverviewPanel(game);
        unitOverviewPanel = new UnitOverviewPanel(game);
        unitDetailsPanel = new UnitDetailsPanel(game);
        structureDetailsPanel = new StructureDetailsPanel(game);
        miniMapPanel = new MiniMapPanel(game);
        makePanel = new MakeDetailsPanel(game);
        commandPanel = new CommandPanel(game);
        mapMakerPanel = new MapMakerPanel(root, this);
        mainMenuPanel = new MainMenuPanel(root, this);
        settingsPanel = new SettingsPanel(root, this);
	}

	public void drawVisiblePanels(int width, int height) {
    	screenDimensions.x = width;
    	screenDimensions.y = height;
    	checkVisibility();
    	for (Panel panel : panels) {
    		if (panel.getIsVisible()) {
    			panel.draw(gc, screenDimensions);
    		}
    	}
    }

    private void checkVisibility() {
    	if (viewMode == ViewEnum.MAIN_GAME) {
    		if (game.getCurrentMode() == ModeEnum.UNIT) {
    			unitDetailsPanel.setIsVisible(true);
    			structureDetailsPanel.setIsVisible(false);
    		} else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
    			unitDetailsPanel.setIsVisible(false);
    			structureDetailsPanel.setIsVisible(true);
    		} else {
    			unitDetailsPanel.setIsVisible(false);
    			structureDetailsPanel.setIsVisible(false);
    		}
    	}
		if (game.isShowingMakeDetails()) {
			makePanel.setIsVisible(true);
		} else {
			makePanel.setIsVisible(false);
		}
	}

	public void updateAnimationTime() {
        gamePanel.updateAnimationCount();
        civPanel.updateAnimationCount();
        modePanel.updateAnimationCount();
        structureOverviewPanel.updateAnimationCount();
        unitDetailsPanel.updateAnimationCount();
    }

    public void renderGame() {
    	double width = scene.getWidth();
    	double height = scene.getHeight();
        canvas.setWidth(width);
        canvas.setHeight(height);
        updateAnimationTime();
        gc.clearRect(0, 0, width, height);
        drawVisiblePanels((int)width, (int)height);
    }
    
    public void startDragging(double x, double y) {
    	camera.startDragging(x, y);
    }
    
    public void continueDragging(double x, double y) {
    	camera.continueDragging(x, y);
    }

	public void zoom(double deltaY) {
		camera.zoom(deltaY);
	}
	

    public void toggleUnitOverview() {
        if (structureOverviewPanel.getIsVisible()) {
            structureOverviewPanel.setIsVisible(false);
        }
        unitOverviewPanel.setIsVisible(!unitOverviewPanel.getIsVisible());
    }

    public void toggleStructureOverview() {
    	if (unitOverviewPanel.getIsVisible()) {
            unitOverviewPanel.setIsVisible(false);
        }
        structureOverviewPanel.setIsVisible(!structureOverviewPanel.getIsVisible());
    }
    
    // Assign showing the make details panel
    public void mainGameMode() {
    	game.initializeGame();
    	viewMode = ViewEnum.MAIN_GAME;
        civPanel.setIsVisible(true);
        modePanel.setIsVisible(true);
        gamePanel.setIsVisible(true);
        structureOverviewPanel.setIsVisible(false);
        unitOverviewPanel.setIsVisible(false);
        structureDetailsPanel.setIsVisible(false);
        unitDetailsPanel.setIsVisible(false);
        miniMapPanel.setIsVisible(true);
        makePanel.setIsVisible(true);
        commandPanel.setIsVisible(true);
        mainMenuPanel.setIsVisible(false);
        settingsPanel.setIsVisible(false);
        mapMakerPanel.setIsVisible(false);
    }

    public void mapMakerMode() {
    	viewMode = ViewEnum.MAP_MAKER;
        civPanel.setIsVisible(false);
        modePanel.setIsVisible(false);
        gamePanel.setIsVisible(false);
        structureOverviewPanel.setIsVisible(false);
        unitOverviewPanel.setIsVisible(false);
        structureDetailsPanel.setIsVisible(false);
        unitDetailsPanel.setIsVisible(false);
        miniMapPanel.setIsVisible(false);
        makePanel.setIsVisible(false);
        commandPanel.setIsVisible(false);
        mainMenuPanel.setIsVisible(false);
        settingsPanel.setIsVisible(false);
        mapMakerPanel.setIsVisible(true);
    }
    
    public void mainMenuMode() {
    	viewMode = ViewEnum.MAIN_MENU;
        civPanel.setIsVisible(false);
        modePanel.setIsVisible(false);
        gamePanel.setIsVisible(false);
        structureOverviewPanel.setIsVisible(false);
        unitOverviewPanel.setIsVisible(false);
        structureDetailsPanel.setIsVisible(false);
        unitDetailsPanel.setIsVisible(false);
        miniMapPanel.setIsVisible(false);
        makePanel.setIsVisible(false);
        commandPanel.setIsVisible(false);
        mapMakerPanel.setIsVisible(false);
        mainMenuPanel.setIsVisible(true);
        settingsPanel.setIsVisible(false);
    }
    
    public void settingsMode() {
    	viewMode = ViewEnum.SETTINGS;
        civPanel.setIsVisible(false);
        modePanel.setIsVisible(false);
        gamePanel.setIsVisible(false);
        structureOverviewPanel.setIsVisible(false);
        unitOverviewPanel.setIsVisible(false);
        structureDetailsPanel.setIsVisible(false);
        unitDetailsPanel.setIsVisible(false);
        miniMapPanel.setIsVisible(false);
        makePanel.setIsVisible(false);
        commandPanel.setIsVisible(false);
        mapMakerPanel.setIsVisible(false);
        mainMenuPanel.setIsVisible(false);
        settingsPanel.setIsVisible(true);
    }

	public void paintMap(double x, double y) {
		if (viewMode == ViewEnum.MAP_MAKER) {
			mapMakerPanel.tileClicked(new Point((int)x, (int)y));
		}
	}
}
