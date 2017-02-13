package view.game;

import game.Game;
import game.gameboard.Location;
import game.gameboard.TerrainEnum;

public class TileDrawer {
	private GamePanel gamePanel;
	private Game game;
	
	public TileDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	protected void drawTile(int x, int y, TerrainEnum type) {
			switch(type) {
				case GRASS:
					gamePanel.drawStaticTileElement(x, y, "TERRAIN_GRASS");
					break;
				case SAND:
					gamePanel.drawStaticTileElement(x, y, "TERRAIN_SAND");
					break;
				case WATER:
					gamePanel.drawAnimatedTileElement(x, y, "TERRAIN_WATER1", "TERRAIN_WATER2", "TERRAIN_WATER3");
					break;
				case INVISIBLE:
					break;
			}
	}
	
	protected void drawMovingTiles() {
		for (Location moveLocation : game.getMoveLocations()) {
			if (game.getGameBoard().getTiles()[moveLocation.getX()][moveLocation.getY()].getTileType() != TerrainEnum.INVISIBLE ) {
				gamePanel.drawStaticTileElement(moveLocation.getX(), moveLocation.getY(), "MOVE_SELECTED");
			}
		}
	}
}
