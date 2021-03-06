package view.game;

import game.Game;
import game.gameboard.Location;
import game.gameboard.TerrainEnum;
import view.Point;

public class TileDrawer {
    private GamePanel gamePanel;
    private Game game;

    public TileDrawer(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;
    }

    protected void drawTile(Point p, TerrainEnum type) {
        switch (type) {
            case GRASS:
            	gamePanel.drawAnimatedTileElement(p, "TERRAIN_GRASS1", "TERRAIN_GRASS2", "TERRAIN_GRASS3");
                break;
            case SAND:
                gamePanel.drawStaticTileElement(p, "TERRAIN_SAND");
                break;
            case WATER:
                gamePanel.drawAnimatedTileElement(p, "TERRAIN_WATER1", "TERRAIN_WATER2", "TERRAIN_WATER3");
                break;
            case MOUNTAIN:
                gamePanel.drawAnimatedTileElement(p, "TERRAIN_MOUNTAIN1", "TERRAIN_MOUNTAIN2", "TERRAIN_MOUNTAIN3");
                break;
            case INVISIBLE:
                break;
        }
    }

    protected void drawMovingTiles() {
        for (Location moveLocation : game.getMoveLocations()) {
            if (game.getGameBoard().getTiles()[moveLocation.getX()][moveLocation.getY()].getTileType() != TerrainEnum.INVISIBLE) {
                gamePanel.drawStaticTileElement(new Point(moveLocation.getX(), moveLocation.getY()), "MOVE_SELECTED");
            }
        }
    }
}
