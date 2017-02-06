package view;
import game.Game;

import java.awt.Graphics;

import controls.ModeEnum;

public class View {
	Game game;
	CivilizationPanel civPanel;
	ControlModePanel modePanel;
	GamePanel gamePanel;
	StructureOverviewPanel structureOverviewPanel;
	UnitOverviewPanel unitOverviewPanel;
	StructureDetailsPanel structureDetailsPanel;
	UnitDetailsPanel unitDetailsPanel;
	MiniPanel	miniPanel;
	
	public View(Game game) {
		this.game = game;
		civPanel = new CivilizationPanel(game);
		modePanel = new ControlModePanel(game);
		gamePanel = new GamePanel(game);
		structureOverviewPanel = new StructureOverviewPanel(game);
		unitOverviewPanel = new UnitOverviewPanel(game);
		unitDetailsPanel = new UnitDetailsPanel(game);
		structureDetailsPanel = new StructureDetailsPanel(game);
		miniPanel = new MiniPanel(game);
	}
	
	public void drawVisiblePanels(Graphics g, int width, int height) {
		//Add structure And unit Overview Modes
		gamePanel.draw(g, width, height);
		civPanel.draw(g, width, height);
		modePanel.draw(g, width, height);
		if (game.getCurrentMode() == ModeEnum.UNIT)
			unitDetailsPanel.draw(g, width, height);
		if (game.getCurrentMode() == ModeEnum.STRUCTURE)
			structureDetailsPanel.draw(g, width, height);
		miniPanel.draw(g, width, height);
		//unitOverviewPanel.drawPanelBox(g, width, height);
	}

	public void updateAnimationTime() {
		gamePanel.updateAnimationCount();
		civPanel.updateAnimationCount();
		modePanel.updateAnimationCount();
		structureOverviewPanel.updateAnimationCount();
		unitDetailsPanel.updateAnimationCount();	
	}
}
