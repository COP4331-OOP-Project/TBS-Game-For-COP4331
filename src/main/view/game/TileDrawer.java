package view.game;

import game.Game;
import game.gameboard.Location;

public class TileDrawer {
	private GamePanel gamePanel;
	private Game game;
	
	public TileDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	protected void drawTile(int x, int y, int type) {
		if (type != -1) {
			switch(type) {
				case 0:
					gamePanel.drawStaticTileElement(x, y, "TERRAIN_GRASS");
					break;
				case 1:
					gamePanel.drawStaticTileElement(x, y, "TERRAIN_SAND");
					break;
				case 2:
					gamePanel.drawAnimatedTileElement(x, y, "TERRAIN_WATER1", "TERRAIN_WATER2", "TERRAIN_WATER3");
					break;
			}
		}
	}
	
	protected void drawMovingTiles() {
		for (Location moveLocation : game.getMoveLocations()) {
			if (game.getGameBoard().getTiles()[moveLocation.getX()][moveLocation.getY()].getTileType() != -1 ) {
				gamePanel.drawStaticTileElement(moveLocation.getX(), moveLocation.getY(), "MOVE_SELECTED");
			}
		}
	}
}
