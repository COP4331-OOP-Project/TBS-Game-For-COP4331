package view.game;

import game.Assets;
import game.Game;
import game.gameboard.Location;
import game.gameboard.TerrainEnum;
import javafx.scene.image.Image;
import view.Animation;
import view.Point;

public class TileDrawer {
    private GamePanel gamePanel;
    private Game game;
    Animation grassAnimation = new Animation(new Image[] { Assets.getInstance().getImage("TERRAIN_GRASS1"),
    														Assets.getInstance().getImage("TERRAIN_GRASS2"), 
    														Assets.getInstance().getImage("TERRAIN_GRASS3")}, 30);
 
    Animation waterAnimation = new Animation(new Image[] { Assets.getInstance().getImage("TERRAIN_WATER3"),
															Assets.getInstance().getImage("TERRAIN_WATER2"), 
															Assets.getInstance().getImage("TERRAIN_WATER1")}, 25);

    Animation mountainAnimation = new Animation(new Image[] { Assets.getInstance().getImage("TERRAIN_MOUNTAIN1"),
															Assets.getInstance().getImage("TERRAIN_MOUNTAIN2"), 
															Assets.getInstance().getImage("TERRAIN_MOUNTAIN3")}, 15);


    public TileDrawer(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;
    }

    protected void drawTile(Point p, TerrainEnum type) {
        switch (type) {
            case GRASS:
            	gamePanel.drawAnimatedTileElement(p, grassAnimation);
                break;
            case SAND:
                gamePanel.drawStaticTileElement(p, "TERRAIN_SAND");
                break;
            case WATER:
                gamePanel.drawAnimatedTileElement(p, waterAnimation);
                break;
            case MOUNTAIN:
                gamePanel.drawAnimatedTileElement(p, mountainAnimation);
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
