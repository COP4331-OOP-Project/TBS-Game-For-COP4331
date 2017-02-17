package view;

import controls.ModeEnum;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import view.game.GamePanel;
import view.gui.*;

public class View {
    Game game;
    CivilizationPanel civPanel;
    ControlModePanel modePanel;
    GamePanel gamePanel;
    StructureOverviewPanel structureOverviewPanel;
    UnitOverviewPanel unitOverviewPanel;
    StructureDetailsPanel structureDetailsPanel;
    UnitDetailsPanel unitDetailsPanel;
    MiniMapPanel miniPanel;
    MakeDetailsPanel makePanel;
    GraphicsContext gc;
    
    private boolean isDragging = false;
    private double dragX = 0;
    private double dragY = 0;
    
    public View(Game game, GraphicsContext gc) {
        this.gc = gc;
        this.game = game;
        civPanel = new CivilizationPanel(game);
        modePanel = new ControlModePanel(game);
        gamePanel = new GamePanel(game);
        structureOverviewPanel = new StructureOverviewPanel(game);
        unitOverviewPanel = new UnitOverviewPanel(game);
        unitDetailsPanel = new UnitDetailsPanel(game);
        structureDetailsPanel = new StructureDetailsPanel(game);
        miniPanel = new MiniMapPanel(game);
        makePanel = new MakeDetailsPanel(game);
    }

    public void drawVisiblePanels(int width, int height) {
        //Add structure And unit Overview Modes
        gamePanel.draw(gc, width, height);

        civPanel.draw(gc, width, height);
        modePanel.draw(gc, width, height);
        if (game.getCurrentMode() == ModeEnum.UNIT)
            unitDetailsPanel.draw(gc, width, height);
        if (game.getCurrentMode() == ModeEnum.STRUCTURE)
            structureDetailsPanel.draw(gc, width, height);
        miniPanel.draw(gc, width, height);
        if (game.getUnitOverviewVisible())
            unitOverviewPanel.draw(gc, width, height);
        if (game.getStructureOverviewVisible())
            structureOverviewPanel.draw(gc, width, height);
        //unitOverviewPanel.drawPanelBox(g, width, height);
        if (game.isShowingMakeDetails()) makePanel.draw(gc, width, height);


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

	public void zoom(double deltaY, double mouseX, double mouseY) {
		gamePanel.zoom(deltaY, mouseX, mouseY);
	}

	public void clickedOn(double x, double y) {
		Point p = new Point((int)x, (int)y);
	}
	
}
