package view;

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
    private GraphicsContext gc;
    private Point screenDimensions;
    private boolean isDragging = false;
    private double dragX = 0;
    private double dragY = 0;
    
    public View(Game game, GraphicsContext gc) {
        this.gc = gc;
        this.game = game;
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
    }

    public void drawVisiblePanels(int width, int height) {
    	screenDimensions.x = width;
    	screenDimensions.y = height;
        //Add structure And unit Overview Modes
        gamePanel.draw(gc, screenDimensions);

        civPanel.draw(gc, screenDimensions);
        modePanel.draw(gc, screenDimensions);
        if (game.getCurrentMode() == ModeEnum.UNIT)
            unitDetailsPanel.draw(gc, screenDimensions);
        if (game.getCurrentMode() == ModeEnum.STRUCTURE)
            structureDetailsPanel.draw(gc, screenDimensions);
        miniMapPanel.draw(gc, screenDimensions);
        if (game.getUnitOverviewVisible())
            unitOverviewPanel.draw(gc, screenDimensions);
        if (game.getStructureOverviewVisible())
            structureOverviewPanel.draw(gc, screenDimensions);
        //unitOverviewPanel.drawPanelBox(g, width, height);
        if (game.isShowingMakeDetails()) makePanel.draw(gc, screenDimensions);


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
}
