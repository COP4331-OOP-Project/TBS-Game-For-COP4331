package view.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.Game;

public class StructureDrawer {

	private final static Logger log = LogManager.getLogger(StructureDrawer.class);
	
	GamePanel gamePanel;
	Game game;
	
	StructureDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	protected void drawBase(int x, int y, int player, int rotation) {
		switch(player) {
			case 0:
				gamePanel.drawStaticTileElement(x, y, "BASE_O");
				break;
			case 1:
				gamePanel.drawStaticTileElement(x, y, "BASE_B");
				break;
			case 2:
				gamePanel.drawStaticTileElement(x, y, "BASE_Y");
				break;
			case 3:
				gamePanel.drawStaticTileElement(x, y, "BASE_G");
				break;
			default:
				log.warn("Invalid player specific for drawing base");
		}
		gamePanel.drawStaticTileElement(x, y, rotation, "BASE_ARROW");
	}
}
