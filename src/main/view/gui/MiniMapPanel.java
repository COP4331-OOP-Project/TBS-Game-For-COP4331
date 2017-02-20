package view.gui;

import game.Assets;
import game.Game;
import game.entities.units.Unit;
import game.gameboard.TerrainEnum;
import game.gameboard.Tile;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.Point;

public class MiniMapPanel extends Panel {
    private static final int HEX_SIZE = 12;
    private static final int HEX_HEIGHT = 10;
    private static final int DISTANCE_FROM_RIGHT = 400;
    private static final int DISTANCE_FROM_BOTTOM = 530;
    private final static Logger log = LogManager.getLogger(MiniMapPanel.class);
    Game game;
    private Point screenDimensions;

    public MiniMapPanel(Game game) {
        this.game = game;
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
        this.screenDimensions = screenDimensions;
        for (int i = 0; i < game.getGameBoard().getTiles().length; i++) {
            for (int j = 0; j < game.getGameBoard().getTiles()[i].length; j++) {
            	Point loc = new Point(i, j);
            	Tile tile = game.getGameBoard().getTiles()[i][j];
                drawSmallTile(gc, loc, tile.getTileType());

                if (tile.containsStructure) {
                    drawSmallStructure(new Point(tile.getStructure().getLocation().getX(),
                            tile.getStructure().getLocation().getY()),
                            tile.getStructure().getOwnerID(), gc);
                }
                if (tile.containsUnit) {
                    for (Unit unit : tile.getUnits()) {
                        if (!tile.containsArmy && !tile.containsArmy) {
                            drawSmallUnit(new Point(tile.getLocation().getX(), tile.getLocation().getY()),
                                    unit.getOwnerID(), gc);
                        }
                    }
                }
            }
        }
        drawBorder(gc);
    }

    private void drawSmallStructure(Point tileLoc, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(Assets.getInstance().getImage("BASE_O_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            gc.drawImage(Assets.getInstance().getImage("BASE_B_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }

    private void drawSmallUnit(Point tileLoc, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(Assets.getInstance().getImage("UNIT_O_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            gc.drawImage(Assets.getInstance().getImage("UNIT_B_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }

    private void drawBorder(GraphicsContext gc) {
        gc.drawImage(Assets.getInstance().getImage("GUI_MINI_MAP_BORDER"), screenDimensions.x - DISTANCE_FROM_RIGHT - 27
                , screenDimensions.y - DISTANCE_FROM_BOTTOM + 182);

    }

    private void drawSmallTile(GraphicsContext gc, Point tileLoc, TerrainEnum tileType) {
        switch (tileType) {
            case GRASS:
                gc.drawImage(Assets.getInstance().getImage("GRASS_MINI"),
                        offsetMini(tileLoc).x, offsetMini(tileLoc).y);
                break;
            case SAND:
                gc.drawImage(Assets.getInstance().getImage("SAND_MINI"),
                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
                break;
            case WATER:
                gc.drawImage(Assets.getInstance().getImage("WATER_MINI"),
                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
                break;
            case MOUNTAIN:
                gc.drawImage(Assets.getInstance().getImage("MOUNTAIN_MINI"),
                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
                break;
            case INVISIBLE:
                break;
            default:
                log.warn("Invalid tile type on minimap: " + tileType);
        }
    }

    private Point offsetMini(Point p) {
        Point offsetPoint = new Point();
        offsetPoint.x = screenDimensions.x + ((int) (((p.x * 0.5f) * HEX_SIZE / 2) + (p.x * 0.5f) * HEX_SIZE)) -
                DISTANCE_FROM_RIGHT;
        offsetPoint.y = ((int) ((p.y * HEX_HEIGHT) + ((p.x * 0.5f) * HEX_HEIGHT))) + screenDimensions.y -
                DISTANCE_FROM_BOTTOM;
        return offsetPoint;
    }

	@Override
	public void hideGUIElements() {
		
	}

	@Override
	public void showGUIElements() {
		// TODO Auto-generated method stub
		
	}
}
