package view;
import game.Game;
import view.game.GamePanel;
import view.gui.CivilizationPanel;
import view.gui.ControlModePanel;
import view.gui.MakeDetailsPanel;
import view.gui.MiniMapPanel;
import view.gui.StructureDetailsPanel;
import view.gui.StructureOverviewPanel;
import view.gui.UnitDetailsPanel;
import view.gui.UnitOverviewPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
	MiniMapPanel miniPanel;
	MakeDetailsPanel makePanel;
	
	public View(Game game) {
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
	
	public void drawVisiblePanels(Graphics2D g, int width, int height) {
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		//Add structure And unit Overview Modes
		gamePanel.draw(g, width, height);
		civPanel.draw(g, width, height);
		modePanel.draw(g, width, height);
		if (game.getCurrentMode() == ModeEnum.UNIT)
			unitDetailsPanel.draw(g, width, height);
		if (game.getCurrentMode() == ModeEnum.STRUCTURE)
			structureDetailsPanel.draw(g, width, height);
		miniPanel.draw(g, width, height);
		if (game.getUnitOverviewVisible())
			unitOverviewPanel.draw(g, width, height);
		if (game.getStructureOverviewVisible())
			structureOverviewPanel.draw(g, width, height);
		//unitOverviewPanel.drawPanelBox(g, width, height);
		if (game.isShowingMakeDetails()) makePanel.draw(g, width, height);

	}

	public void updateAnimationTime() {
		gamePanel.updateAnimationCount();
		civPanel.updateAnimationCount();
		modePanel.updateAnimationCount();
		structureOverviewPanel.updateAnimationCount();
		unitDetailsPanel.updateAnimationCount();
	}
}
