package view.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.Game;
import game.entities.structures.Structure;
import game.gameboard.Tile;

public class StructureDrawer {

	private final static Logger log = LogManager.getLogger(StructureDrawer.class);
	
	GamePanel gamePanel;
	Game game;
	
	StructureDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	private void drawBase(int x, int y, int player, int rotation) {
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
	
	void drawBases() {
		for (Tile[] tiles : this.game.getGameBoard().getTiles()) {
			for (Tile tile : tiles) {
				if (tile.containsStructure) {
					Structure structure = tile.getStructure();
					drawBase(tile.getLocation().getX(), tile.getLocation().getY(), 
							structure.getOwnerID(), structure.getRotation());
				}
			}
		}
	}
}
