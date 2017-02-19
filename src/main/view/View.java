package view;

import java.util.ArrayList;

import controls.ModeEnum;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import view.game.Camera;
import view.game.GamePanel;
import view.gui.*;

public class View {
    private Game game;
    private Camera camera;
    private CivilizationPanel civPanel;
    private ControlModePanel modePanel;
    private GamePanel gamePanel;
    private StructureOverviewPanel structureOverviewPanel;
    private UnitOverviewPanel unitOverviewPanel;
    private StructureDetailsPanel structureDetailsPanel;
    private UnitDetailsPanel unitDetailsPanel;
    private MiniMapPanel miniMapPanel;
    private MakeDetailsPanel makePanel;
    
    private ArrayList<Panel> panels;
    private GraphicsContext gc;
    private Point screenDimensions;
    private boolean isDragging = false;
    private double dragX = 0;
    private double dragY = 0;
    
    public View(Game game, GraphicsContext gc) {
        this.gc = gc;
        this.game = game;
        panels = new ArrayList<Panel>();
        screenDimensions = new Point();
        camera = new Camera(screenDimensions);
        
        civPanel = new CivilizationPanel(game);
        modePanel = new ControlModePanel(game);
        gamePanel = new GamePanel(game, camera);
        structureOverviewPanel = new StructureOverviewPanel(game);
        unitOverviewPanel = new UnitOverviewPanel(game);
        unitDetailsPanel = new UnitDetailsPanel(game);
        structureDetailsPanel = new StructureDetailsPanel(game);
        miniMapPanel = new MiniMapPanel(game);
        makePanel = new MakeDetailsPanel(game);

        panels.add(gamePanel);
        panels.add(civPanel);
        panels.add(modePanel);
        panels.add(structureOverviewPanel);
        panels.add(unitOverviewPanel);
        panels.add(unitDetailsPanel);
        panels.add(structureDetailsPanel);
        panels.add(miniMapPanel);
        panels.add(makePanel);
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

    public void renderGame(int width, int height) {
        updateAnimationTime();
        gc.clearRect(0, 0, width, height);
        drawVisiblePanels(width, height);
    }
    
	public void setDragging(double x, double y) {
		dragX = x;
		dragY = y;
		isDragging = true;
	}

	public void setStoppedDragging() {
		isDragging = false;
	}

	public void updateViewLocation(double x, double y) {
		double diffX = dragX - x;
		double diffY = dragY - y;
		gamePanel.moveCamera(diffX, diffY);
		dragX = x;
		dragY = y;
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



}
