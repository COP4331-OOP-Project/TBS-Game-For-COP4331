package view.game;

import game.Assets;
import game.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import controls.ModeEnum;
import controls.unit.UnitEnum;
import game.commands.MoveCommand;
import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Location;

import game.gameboard.Tile;
import view.Panel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GamePanel extends Panel {
	private final static Logger log = LogManager.getLogger(GamePanel.class);
	private static final int TILE_PIXEL_SIZE = 
			Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
	private Camera camera;
	private TileDrawer tileDrawer;
	private UnitDrawer unitDrawer;
	private ArmyDrawer armyDrawer;
	private StructureDrawer structureDrawer;
	private SelectedDrawer selectedDrawer;
	
	private Game game;
	private Graphics2D g2d;
	 
	Font tileFont = new Font("Lucida Sans", Font.PLAIN, 20);
	
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
		camera.getPanelCenterer().checkCentering(5, 5);
		//panelCenterer.checkWindowCentered(width, height);
		g2d = (Graphics2D)g;
		tileDrawer.drawTiles();
		tileDrawer.drawMovingTiles();
		structureDrawer.drawBases();
		armyDrawer.drawArmies();
		unitDrawer.drawUnits();
		drawSelectedItem(game.getCurrentMode() == ModeEnum.ARMY);
	}
	
	void drawSelectedItem(boolean isArmyUnit) {
		selectedDrawer.drawSelectedItem(isArmyUnit);
	}
	
	protected void drawStaticTileElement(int x, int y, String image) {
		g2d.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x), 
				camera.offsetY(y), null); 
	}
	
	protected void drawStaticTileElement(int x, int y, int rotation, String image) {
		AffineTransform currentRotation = g2d.getTransform();
		rotateOnTile(x, y, rotation);
		g2d.drawImage(Assets.getInstance().getImage(image), camera.offsetX(x), 
				camera.offsetY(y), null); 
		g2d.setTransform(currentRotation);
	}
	
	private void rotateOnTile(int x, int y, int degrees) {
		g2d.rotate(Math.toRadians(degrees), 
				camera.getTileLocation(x) + TILE_PIXEL_SIZE/2, 
				camera.getTileLocation(y) + TILE_PIXEL_SIZE/2);
	}
	
	protected void drawAnimatedTileElement(int x, int y, 
			String image1, String image2, String image3) {
		switch (getAnimationImage()) {
		case 0:
			g2d.drawImage(Assets.getInstance().getImage(image1), camera.offsetX(x), camera.offsetY(y), null);
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
}
