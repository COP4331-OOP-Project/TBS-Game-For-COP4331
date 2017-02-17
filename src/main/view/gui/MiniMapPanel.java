package view.gui;

import game.Assets;
import game.Game;
import game.entities.units.Unit;
import game.gameboard.TerrainEnum;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Panel;

public class MiniMapPanel extends Panel {
    private static final int HEX_SIZE = 12;
    private static final int HEX_HEIGHT = 10;
    private static final int DISTANCE_FROM_RIGHT = 400;
    private static final int DISTANCE_FROM_BOTTOM = 530;
    private final static Logger log = LogManager.getLogger(MiniMapPanel.class);
    Game game;
    private int width;
    private int height;

    public MiniMapPanel(Game game) {
        this.game = game;
    }

    public void draw(GraphicsContext gc, int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < game.getGameBoard().getTiles().length; i++) {
            for (int j = 0; j < game.getGameBoard().getTiles()[i].length; j++) {
                drawSmallTile(gc, i, j, game.getGameBoard().getTiles()[i][j].getTileType());

                if (game.getGameBoard().getTiles()[i][j].containsStructure) {
                    drawSmallStructure(game.getGameBoard().getTiles()[i][j].getStructure().getLocation().getX(),
                            game.getGameBoard().getTiles()[i][j].getStructure().getLocation().getY(),
                            game.getGameBoard().getTiles()[i][j].getStructure().getOwnerID(), gc);
                }
                if (game.getGameBoard().getTiles()[i][j].containsUnit) {
                    for (Unit unit : game.getGameBoard().getTiles()[i][j].getUnits()) {
                        if (!game.getGameBoard().getTiles()[i][j].containsArmy && !game.getGameBoard().getTiles()[i][j].containsArmy) {
                            drawSmallUnit(game.getGameBoard().getTiles()[i][j].getLocation().getX(),
                                    game.getGameBoard().getTiles()[i][j].getLocation().getY(),
                                    unit.getOwnerID(), gc);
                        }
                    }
                }
            }
        }
        drawBorder(gc);
    }

    private void drawSmallStructure(int x, int y, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(Assets.getInstance().getImage("BASE_O_SMALL"), offX(x, y), offY(x, y));

        } else {
            gc.drawImage(Assets.getInstance().getImage("BASE_B_SMALL"), offX(x, y), offY(x, y));
        }
    }

    private void drawSmallUnit(int x, int y, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(Assets.getInstance().getImage("UNIT_O_SMALL"), offX(x, y), offY(x, y));

        } else {
            gc.drawImage(Assets.getInstance().getImage("UNIT_B_SMALL"), offX(x, y), offY(x, y));
        }
    }

    private void drawBorder(GraphicsContext gc) {
        gc.drawImage(Assets.getInstance().getImage("GUI_MINI_MAP_BORDER"), width - DISTANCE_FROM_RIGHT - 27
                , height - DISTANCE_FROM_BOTTOM + 182);

    }

    private void drawSmallTile(GraphicsContext gc, int x, int y, TerrainEnum tileType) {
        switch (tileType) {
            case GRASS:
                gc.drawImage(Assets.getInstance().getImage("GRASS_MINI"),
                        offX(x, y), offY(x, y));
                break;
            case SAND:
                gc.drawImage(Assets.getInstance().getImage("SAND_MINI"),
                        offX(x, y), offY(x, y));
                break;
            case WATER:
                gc.drawImage(Assets.getInstance().getImage("WATER_MINI"),
                        offX(x, y), offY(x, y));
                break;
            case INVISIBLE:
                break;
            default:
                log.warn("Invalid tile type on minimap: " + tileType);
        }
    }

    private int offX(int x, int y) {
        return this.width + ((int) (((x * 0.5f) * HEX_SIZE / 2) + (x * 0.5f) * HEX_SIZE)) -
                DISTANCE_FROM_RIGHT;
    }

    private int offY(int x, int y) {
        return ((int) ((y * HEX_HEIGHT) + ((x * 0.5f) * HEX_HEIGHT))) + this.height -
                DISTANCE_FROM_BOTTOM;
    }
}
