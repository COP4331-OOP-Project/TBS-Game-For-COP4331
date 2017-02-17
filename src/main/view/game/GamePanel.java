package view.game;

import java.util.ArrayList;

import game.Assets;
import game.Game;
import game.entities.Army;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import view.Panel;
import view.Point;

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
    
	private boolean zooming = false;
	private int zoomCounter = -1;
	Point mouseZoomStart = new Point(0,0);
	private int width;
	private int height;

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
    	this.width = width;
    	this.height = height;
        this.gc = gc;
        checkZooming();
        if (!zooming) {
        	camera.getPanelCenterer().recenter(width, height);
        }
        Point selected = new Point(game.getCenterCoordinates().getX(),
        						   game.getCenterCoordinates().getY());
        if (selected.x != 0 && selected.y != 0) {
            camera.getPanelCenterer().centerOnTile(selected);
        }

        drawAllItems();
        selectedDrawer.drawSelectedItemOutline();
        //Draw Moving Tiles
        tileDrawer.drawMovingTiles();
    }

	private void checkZooming() {
    	if (zoomCounter != -1) {
    		zoomCounter++;
    		if (zoomCounter > 20) {
    			zoomCounter = -1;
    			zooming = false;
    			camera.getPanelCenterer().stopCentering();
    		}
    	}
	}

	private void drawAllItems() {
        for (int i = 0; i < game.getGameBoard().getTiles().length; i++) {
            for (int j = 0; j < game.getGameBoard().getTiles()[i].length; j++) {
                Tile tile = game.getGameBoard().getTiles()[i][j];
                Point p = new Point(tile.getLocation().getX(), tile.getLocation().getY());

                //Draw Tiles
                tileDrawer.drawTile(p, tile.getTileType());
                if (tile.getUnits().size() > 1 && !tile.containsArmy) {
                    getgc().fillText("" + tile.getUnits().size()
                            , getCamera().offset(p).x + 5, getCamera().offset(p).y + 22);
                }
                //Draw Structures
                if (tile.containsStructure) {
                    Structure structure = tile.getStructure();
                    structureDrawer.drawBase(p,
                            structure.getOwnerID(), structure.getRotation());
                }

                //Draw Armies
                if (tile.containsArmy) {
                    for (Army army : tile.getArmies()) {
                        armyDrawer.drawArmy(p, army.getOwnerID(),
                                army.getRotation(), army.getAllUnits().size());
                    }
                } else if (tile.containsArmy && tile.getUnits().size() > 0) {
                    // this is so wrong but might work for demo
                    armyDrawer.drawArmy(p,
                            tile.getUnits().get(0).getOwnerID(), 0, tile.getUnits().size()); // lol
                }
                //Draw Units
                if (tile.containsUnit) {
                    for (Unit unit : tile.getUnits()) {
                        if (!tile.containsArmy) {
                            unitDrawer.drawUnit(p, unit.getUnitType(), unit.getOwnerID(), 0);
                        }
                    }
                }
            }
        }
    }

    public void drawStaticTileElement(Point p, String image) {
    	Image img = Assets.getInstance().getImage(image);
    	gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
        		camera.getScale() * img.getHeight());
    }

    public void drawStaticTileElement(Point p, int rotation, String image) {
        Image img = Assets.getInstance().getImage(image);
    	Affine currentRotation = gc.getTransform();
        rotateOnTile(p, rotation);
        gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
        		camera.getScale() * img.getHeight());
        gc.setTransform(currentRotation);
    }

    private void rotateOnTile(Point p, int degrees) {
        Rotate rotate = new Rotate(degrees,
                (double) (camera.getTileCenter(p).x),
                (double) (camera.getTileCenter(p).y));
        gc.getTransform().setToTransform(rotate);
    }

    public void drawAnimatedTileElement(Point p, String image1, String image2, String image3) {
        Image img;   
    	switch (getAnimationImage()) { 
	        	case 0:
	            	img = Assets.getInstance().getImage(image1);
	                break;
	            case 2:
	            	img = Assets.getInstance().getImage(image2);
	                break;
	            default:
	            	img = Assets.getInstance().getImage(image3);
        }
    	
        gc.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
        		camera.getScale() * img.getHeight());
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
		camera.setOffset(new Point(camera.getOffset().x - (int)diffX,
				(camera.getOffset().y - (int)diffY)));
	}

	public void zoom(double deltaY) {
		Point p = new Point((int)width/2, (int)height/2);
		zoomCounter = 0;
		if (!zooming) {
			mouseZoomStart = camera.getTileLocation(p);
		}
		zooming = true;
		camera.zoom(deltaY, mouseZoomStart);
	}
}
