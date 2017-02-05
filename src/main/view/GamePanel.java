package view;

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
import controls.Unit.UnitEnum;
import game.entities.units.Unit;
import game.gameboard.Location;

import game.entities.units.Colonist;
import game.entities.units.Explorer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GamePanel extends Panel {

	Game game;
	private static final int TILE_PIXEL_SIZE = 100;
	private static final int TIME_TO_CENTER = 50;
	private final static Logger log = LogManager.getLogger(GamePanel.class);
	
	private int offsetX = 0;
	private int offsetY = 0;
	
	private int timeCentering = 0;
	private boolean isCentering = false;
	private int centerStartX = -1;
	private int centerStartY = -1;
	private int centerToX = -1;
	private int centerToY = -1;
	
	private int selectedX = -1;
	private int selectedY = -1;
	
	private int width = 0;
	private int height = 0;

	//ArrayList of each player's unit
	private ArrayList<Unit> player1Units;
	private ArrayList<Unit> player2Units;

	Graphics2D g2d;
	
	//TEST ELEMENTS BELOW
	Random rand = new Random(); 
	Font armyFont = new Font("Lucida Sans", Font.BOLD, 40);
	private static final int NUM_TILES = 20; 
	int[][] tiles = new int[NUM_TILES][NUM_TILES];
	//END TEST ELEMENTS
	
	
	public GamePanel(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g, int width, int height) {
		g2d = (Graphics2D)g;
		this.width = width;
		this.height = height;
		if (isCentering)
			continueCentering();
		checkCenteringCoordinates();
		drawTiles();
		drawBases();
		drawUnits();
		drawArmies();
		drawSelectedItem();
	}

	private void checkCenteringCoordinates() {
		if (this.game.isCenterCoordinatesUpdated()) {
			Location loc = this.game.getCenterCoordinates();
			selectedX = loc.getX();
			selectedY = loc.getY();
			this.checkCentering(selectedX, selectedY);
			this.game.setCenterCoordinatesUpdated(false);
		}
	}

	private void checkCentering(int x, int y) {
		if (centeringOffsetX(x) != centerToX || 
				centeringOffsetY(y) != centerToY) {
			centerOnTile(x, y);
		}
		
	}
	
	private void drawSelectedItem() {
		if (!(selectedX == -1 && selectedY == -1)) {
			int x = selectedX;
			int y = selectedY;
			if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
				drawStaticTileElement(x, y, "RALLY_POINT_SELECTED");
			}
			if (game.getCurrentMode() == ModeEnum.UNIT) {
				drawStaticTileElement(x, y, "UNIT_SELECTED");
			}
			if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
				drawStaticTileElement(x, y, "BASE_SELECTED");
			}
			if (game.getCurrentMode() == ModeEnum.ARMY) {
				drawStaticTileElement(x, y, "UNIT_SELECTED");
			}
		}
	}

	private void drawBases() {
		// TODO Auto-generated method stub
		
	}

	private void drawUnits() {
		int unitSelected = -1;
		player1Units = game.getPlayer(0).getAllUnit();
		for (int i = 0; i < player1Units.size(); i++) {
			if (player1Units.get(i).getLocation().getX() == selectedX
					&& player1Units.get(i).getLocation().getY() == selectedY
					&& selectedX != -1 && selectedY != -1) {
				if (player1Units.get(i).getUnitType() == 0 &&
						game.getCurrentType() == UnitEnum.MELEE)
					unitSelected = i;
				if (player1Units.get(i).getUnitType() == 1 &&
						game.getCurrentType() == UnitEnum.RANGED)
					unitSelected = i;
				if (player1Units.get(i).getUnitType() == 2 &&
						game.getCurrentType() == UnitEnum.EXPLORER)
					unitSelected = i;
				if (player1Units.get(i).getUnitType() == 3 &&
						game.getCurrentType() == UnitEnum.COLONIST)
					unitSelected = i;
			}
			drawUnit(player1Units.get(i).getLocation().getX(),
					player1Units.get(i).getLocation().getY(),
					player1Units.get(i).getUnitType(),
					player1Units.get(i).getOwnerID(),
					0);
		}
		if (game.getCurrentMode() == ModeEnum.UNIT && unitSelected != -1) {
			drawUnit(player1Units.get(unitSelected).getLocation().getX(),
					player1Units.get(unitSelected).getLocation().getY(),
					player1Units.get(unitSelected).getUnitType(),
					player1Units.get(unitSelected).getOwnerID(),
					0);
		} else {
			unitSelected = -1;
			player2Units = game.getPlayer(1).getAllUnit();
			for (int i = 0; i < player2Units.size(); i++) {
				drawUnit(player2Units.get(i).getLocation().getX(),
						player2Units.get(i).getLocation().getY(),
						player2Units.get(i).getUnitType(),
						player2Units.get(i).getOwnerID(),
						0);
			}
		}
	}
	
	/**
	 * This function draws calls the functions
	 * that draw all the tiles
	 */
	private void drawTiles() {
		for (int i = 0; i < game.getGameBoard().gameMap.length; i++) {
			for (int j = 0; j < game.getGameBoard().gameMap[i].length; j++) {
				drawTile(i, j, game.getGameBoard().gameMap[j][i].getTileType());
			}
		}
	}
	
	private void drawArmies() {
		//drawArmy(3,3,1,180,34);
	}
	
	private void drawArmy(int x, int y, int player, int rotation, 
			int numOfUnits) {
		AffineTransform currentRotation = g2d.getTransform();
		rotateOnTile(x, y, rotation);
			switch (player) {
			case 0:
				drawStaticTileElement(x, y, "ARMY_O");
				break;
			case 1:
				drawStaticTileElement(x, y, "ARMY_B");
				break;
			case 2:
				drawStaticTileElement(x, y, "ARMY_Y");
				break;
			case 3:
				drawStaticTileElement(x, y, "ARMY_G");
				break;
			default:
				log.warn("Invalid Player :" + player
						+ " cannot have units drawn");
		}
		g2d.setTransform(currentRotation);
		g2d.setFont(armyFont);
		Color original = g2d.getColor();
		
		if (numOfUnits < 10) {
			g2d.setColor(Color.BLACK);
			g2d.drawString("" + numOfUnits, tileLocation(x) + 
					TILE_PIXEL_SIZE/2 - 15, tileLocation(y) + 
					TILE_PIXEL_SIZE/2 + 18);
			g2d.setColor(Color.WHITE);
			g2d.drawString("" + numOfUnits, tileLocation(x) + 
					TILE_PIXEL_SIZE/2 - 17, tileLocation(y) + 
					TILE_PIXEL_SIZE/2 + 17);
		} else {
			g2d.setColor(Color.BLACK);
			g2d.drawString("" + numOfUnits, tileLocation(x) + 
					TILE_PIXEL_SIZE/2 - 23, tileLocation(y) + 
					TILE_PIXEL_SIZE/2 + 18);
			g2d.setColor(Color.WHITE);
			g2d.drawString("" + numOfUnits, tileLocation(x) + 
					TILE_PIXEL_SIZE/2 - 25, tileLocation(y) + 
					TILE_PIXEL_SIZE/2 + 17);
		}
		g2d.setColor(original);
	}
	
	private void drawBase(int x, int y, int player, int rotation) {
		switch(player) {
			case 0:
				drawStaticTileElement(x, y, "BASE_O");
				break;
			case 1:
				drawStaticTileElement(x, y, "BASE_B");
				break;
			case 2:
				drawStaticTileElement(x, y, "BASE_Y");
				break;
			case 3:
				drawStaticTileElement(x, y, "BASE_G");
				break;
			default:
				log.warn("Invalid player specific for drawing base");
		}
		AffineTransform currentRotation = g2d.getTransform();
		rotateOnTile(x, y, rotation);
		drawStaticTileElement(x, y, "BASE_ARROW");
		g2d.setTransform(currentRotation);
	}
	
	private void drawUnit(int x, int y, int type, int player, 
			int rotation) {
		AffineTransform currentRotation = g2d.getTransform();
		rotateOnTile(x, y, rotation);
		switch (player) {
			case 0:
				drawStaticTileElement(x, y, "UNIT_O");
				break;
			case 1:
				drawStaticTileElement(x, y, "UNIT_B");
				break;
			case 2:
				drawStaticTileElement(x, y, "UNIT_Y");
				break;
			case 3:
				drawStaticTileElement(x, y, "UNIT_G");
				break;
			default:
				log.warn("Invalid Player :" + player
						+ " cannot have units drawn");
		}
		
		switch (type) {
		case 0:
			drawStaticTileElement(x, y, "UNIT_MELEE");
			break;
		case 1:
			drawStaticTileElement(x, y, "UNIT_RANGED");
			break;
		case 2:
			drawStaticTileElement(x, y, "UNIT_EXPLORER");
			break;
		case 3:
			drawStaticTileElement(x, y, "UNIT_COLONIST");
			break;
		default:
			log.warn("Invalid Unit Type :" + type 
					+ " cannot be drawn");
		}
		g2d.setTransform(currentRotation);
	}

	
	
	private void drawTile(int x, int y, int type) {
		switch(type) {
			case 0:
				drawStaticTileElement(x, y, "TERRAIN_GRASS");
				break;
			case 1:
				drawStaticTileElement(x, y, "TERRAIN_SAND");
				break;
			case 2:
				drawAnimatedTileElement(x, y, "TERRAIN_WATER1", "TERRAIN_WATER2", "TERRAIN_WATER3");
				break;
		}
	}
	
	private void drawStaticTileElement(int x, int y, String image) {
		g2d.drawImage(Assets.getInstance().getImage(image), offX(tileLocation(x)), 
				offY(tileLocation(y)), null); 
	}
	
	private void drawAnimatedTileElement(int x, int y, 
			String image1, String image2, String image3) {
		switch (getAnimationImage()) {
		case 0:
			g2d.drawImage(Assets.getInstance().getImage(image1), offX(tileLocation(x)), offY(tileLocation(y)), null);
			break;
		case 2:
			g2d.drawImage(Assets.getInstance().getImage(image2), offX(tileLocation(x)), offY(tileLocation(y)), null);
			break;
		default:
			g2d.drawImage(Assets.getInstance().getImage(image3), offX(tileLocation(x)), offY(tileLocation(y)), null);
		}
	}
	
	public void centerOnTile(int x, int y) {
		if (offsetX == x && offsetY == y)
			return;
		centerStartX = offsetX;
		centerStartY = offsetY;
		centerToX = centeringOffsetX(x);
		centerToY = centeringOffsetY(y);
		isCentering = true;
	}
	
	//Returns the X offset based on the X Location of a tile for centering
	private int centeringOffsetX(int x) {
		return (width/2) - tileLocation(x) - TILE_PIXEL_SIZE/2;
	}
	
	//Returns Y Offset Based on the Y Location of a Tile for centering
	private int centeringOffsetY(int y) {
		return (height/2) - tileLocation(y) - TILE_PIXEL_SIZE/2;
	}
	
	private void continueCentering() {
		if (timeCentering >= TIME_TO_CENTER - 1) {
			offsetX = centerToX;
			offsetY = centerToY;
			timeCentering = 0;
			isCentering = false;
		} else {
			offsetX = (int)((percentDoneCentering()*(centerToX - centerStartX)) 
					+ centerStartX);
			offsetY = (int)((percentDoneCentering()*(centerToY - centerStartY)) 
					+ centerStartY);
			timeCentering++;
		}
	}
	
	//How close to the destination the view is
	private double percentDoneCentering() {
		return (Math.log(((double)timeCentering/(double)TIME_TO_CENTER*(19.1)) + 1)) / 3;
	}

	/**
	 * This takes the location of the tile
	 * in the array, and converts the value
	 * to a pixel location in which it will be displayed
	 */
	private int tileLocation(int value) {
		return value * TILE_PIXEL_SIZE;
	}

	private int offX(int x) {
		return x + offsetX;
	}
	
	private int offY(int y) {
		return y + offsetY;
	}
	
	private void rotateOnTile(int x, int y, int degrees) {
		g2d.rotate(Math.toRadians(degrees), 
				tileLocation(x) + TILE_PIXEL_SIZE/2, tileLocation(y) + TILE_PIXEL_SIZE/2);
	}
}
