package view.game;

import game.Assets;
import game.Game;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Panel;

import java.awt.geom.AffineTransform;


public class GamePanel extends Panel {
    private final static Logger log = LogManager.getLogger(GamePanel.class);
    private static final int TILE_PIXEL_SIZE =
            Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
    Font tileFont = new Font("Lucida Sans", Font.PLAIN, 20);
    private Camera camera;
    private TileDrawer tileDrawer;
    private UnitDrawer unitDrawer;
    private ArmyDrawer armyDrawer;
    private StructureDrawer structureDrawer;
    private SelectedDrawer selectedDrawer;
    private Game game;
    private Graphics2D g2d;
    private int selectedX = 0;
    private int selectedY = 0;

    public GamePanel(Game game) {
        this.game = game;
        camera = new Camera(this, game);
        tileDrawer = new TileDrawer(this, game);
        unitDrawer = new UnitDrawer(this, game);
        armyDrawer = new ArmyDrawer(this, game);
        structureDrawer = new StructureDrawer(this, game);
        selectedDrawer = new SelectedDrawer(this, game);
    }

    public void draw(Graphics g, int width, int height) {
        checkNextPlayer();
        camera.getPanelCenterer().recenter(width, height);
        updateSelectedItem();

        g2d = (Graphics2D) g;
        tileDrawer.drawTiles();
        tileDrawer.drawMovingTiles();
        structureDrawer.drawBases();
        armyDrawer.drawArmies();
        unitDrawer.drawUnits();
        selectedDrawer.drawSelectedItemOutline();
    }

    private void checkNextPlayer() {
        if (!game.getMovedToNewPlayer()) {
            if (game.getCurrentPlayer().getAllUnit().size() > 0) {
                camera.getPanelCenterer().centerOnTile(game.getCurrentPlayer().getAllUnit().
                        get(0).getLocation().getX(), game.getCurrentPlayer().getAllUnit().
                        get(0).getLocation().getY());
            }
            game.setMovedToNewPlayer(true);
        }
    }

    private void updateSelectedItem() {
        if (this.game.isCenterCoordinatesUpdated()) {
            Location loc = this.game.getCenterCoordinates();
            selectedX = loc.getX();
            selectedY = loc.getY();
            camera.getPanelCenterer().centerOnTile(selectedX, selectedY);
            game.setCenterCoordinatesUpdated(false);
        }
    }

    protected void drawStaticTileElement(int x, int y, String image) {
        g2d.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x, y),
                camera.offsetY(y), null);
    }

    protected void drawStaticTileElement(int x, int y, int rotation, String image) {
        AffineTransform currentRotation = g2d.getTransform();
        rotateOnTile(x, y, rotation);
        g2d.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x, y), camera.offsetY(y), null);
        g2d.setTransform(currentRotation);
    }

    private void rotateOnTile(int x, int y, int degrees) {
        g2d.rotate(Math.toRadians(degrees),
                camera.getTileLocationX(x, y) + TILE_PIXEL_SIZE / 2,
                camera.getTileLocationY(x, y) + TILE_PIXEL_SIZE / 2);
    }

    protected void drawAnimatedTileElement(int x, int y,
                                           String image1, String image2, String image3) {
        switch (getAnimationImage()) {
            case 0:
                g2d.drawImage(Assets.getInstance().getImage(image1), camera.offsetX(x, y), camera.offsetY(y), null);
                break;
            case 2:
                g2d.drawImage(Assets.getInstance().getImage(image2), camera.offsetX(x), camera.offsetY(y), null);
                break;
            default:
                g2d.drawImage(Assets.getInstance().getImage(image3), camera.offsetX(x), camera.offsetY(y), null);
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public Graphics2D getG2D() {
        return g2d;
    }

    public int getTileSize() {
        return TILE_PIXEL_SIZE;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }
}
