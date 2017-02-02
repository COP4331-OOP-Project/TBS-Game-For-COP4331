package view;
import java.awt.Graphics;
import game.Game;

public class View {
	Game game;
	CivilizationPanel civPanel;
	ControlModePanel modePanel;
	GamePanel gamePanel;
	StructureOverviewPanel structureOverviewPanel;
	UnitOverviewPanel unitOverviewPanel;
	StructureDetailsPanel structureDetailsPanel;
	UnitDetailsPanel unitDetailsPanel;
	
	public View(Game game) {
		this.game = game;
		civPanel = new CivilizationPanel();
		modePanel = new ControlModePanel();
		gamePanel = new GamePanel(game);
		structureOverviewPanel = new StructureOverviewPanel();
		unitDetailsPanel = new UnitDetailsPanel();
	}
	
	public void drawVisiblePanels(Graphics g, int width, int height) {
		//Add Structure And Unit Overview Modes
		gamePanel.draw(g);
		civPanel.draw(g, width, height);
	}
}
