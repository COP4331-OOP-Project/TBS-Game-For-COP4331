package view.game;

import game.Assets;
import game.Game;
import game.entities.Army;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import view.Panel;

public class GamePanel extends Panel {
    private static final int TILE_PIXEL_SIZE =
            (int) Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
    Font tileFont = new Font("Lucida Sans", 20);
    private Camera camera;
    private TileDrawer tileDrawer;
    private UnitDrawer unitDrawer;
    private ArmyDrawer armyDrawer;
    private StructureDrawer structureDrawer;
    private SelectedDrawer selectedDrawer;
    private GraphicsContext gc;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        camera = new Camera(this);
        tileDrawer = new TileDrawer(this, game);
        unitDrawer = new UnitDrawer(this);
        armyDrawer = new ArmyDrawer(this);
        structureDrawer = new StructureDrawer(this, game);
        selectedDrawer = new SelectedDrawer(this, game);
    }

    public void draw(GraphicsContext gc, int width, int height) {
        this.gc = gc;
        camera.getPanelCenterer().recenter(width, height);
        int selX = game.getCenterCoordinates().getX();
        int selY = game.getCenterCoordinates().getY();
        if (selX != 0 && selY != 0) {
            camera.getPanelCenterer().centerOnTile(selX, selY);
        }

        drawAllItems();
        selectedDrawer.drawSelectedItemOutline();
        //Draw Moving Tiles
        tileDrawer.drawMovingTiles();
    }

    private void drawAllItems() {
        for (int i = 0; i < game.getGameBoard().getTiles().length; i++) {
            for (int j = 0; j < game.getGameBoard().getTiles()[i].length; j++) {
                Tile tile = game.getGameBoard().getTiles()[i][j];
                int x = tile.getLocation().getX();
                int y = tile.getLocation().getY();
                //Draw Tiles
                tileDrawer.drawTile(i, j, tile.getTileType());
                if (tile.getUnits().size() > 1 && !tile.containsArmy) {
                    getgc().fillText("" + tile.getUnits().size()
                            , getCamera().offsetX(i, j) + 5, getCamera().offsetY(i, j) + 22);
                }
                //Draw Structures
                if (tile.containsStructure) {
                    Structure structure = tile.getStructure();
                    structureDrawer.drawBase(x, y,
                            structure.getOwnerID(), structure.getRotation());
                }

                //Draw Armies
                if (tile.containsArmy) {
                    for (Army army : tile.getArmies()) {
                        armyDrawer.drawArmy(x, y, army.getOwnerID(),
                                army.getRotation(), army.getAllUnits().size());
                    }
                } else if (tile.containsArmy && tile.getUnits().size() > 0) {
                    // this is so wrong but might work for demo
                    armyDrawer.drawArmy(x, y,
                            tile.getUnits().get(0).getOwnerID(), 0, tile.getUnits().size()); // lol
                }
                //Draw Units
                if (tile.containsUnit) {
                    for (Unit unit : tile.getUnits()) {
                        if (!tile.containsArmy) {
                            unitDrawer.drawUnit(x, y, unit.getUnitType(), unit.getOwnerID(), 0);
                        }
                    }
                }
            }
        }
    }

    protected void drawStaticTileElement(int x, int y, String image) {
        gc.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x, y),
                camera.offsetY(x, y));
    }

    protected void drawStaticTileElement(int x, int y, int rotation, String image) {
        Affine currentRotation = gc.getTransform();
        rotateOnTile(x, y, rotation);
        gc.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x, y), camera.offsetY(x, y));
        gc.setTransform(currentRotation);
    }

    private void rotateOnTile(int x, int y, int degrees) {
        Rotate rotate = new Rotate(degrees,
                (double) (camera.getTileLocationX(x, y) + TILE_PIXEL_SIZE / 2),
                (double) (camera.getTileLocationY(x, y) + TILE_PIXEL_SIZE / 2));
        gc.getTransform().setToTransform(rotate);
    }

    protected void drawAnimatedTileElement(int x, int y,
                                           String image1, String image2, String image3) {
        switch (getAnimationImage()) {
            case 0:
                gc.drawImage(Assets.getInstance().getImage(image1), camera.offsetX(x, y), camera.offsetY(x, y));
                break;
            case 2:
                gc.drawImage(Assets.getInstance().getImage(image2), camera.offsetX(x, y), camera.offsetY(x, y));
                break;
            default:
                gc.drawImage(Assets.getInstance().getImage(image3), camera.offsetX(x, y), camera.offsetY(x, y));
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public GraphicsContext getgc() {
        return gc;
    }

    public int getTileSize() {
        return TILE_PIXEL_SIZE;
    }

	public void moveCamera(double diffX, double diffY) {
		camera.setX(camera.getX() - (int)diffX);
		camera.setY(camera.getY() - (int)diffY);
	}
}
