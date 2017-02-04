package view;

import game.Assets;
import game.Game;
import game.gameboard.GameBoard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import controls.ModeEnum;

public class GamePanel extends Panel {
	Game game;
	private static final int TILE_PIXEL_SIZE = 100;
	private static final int TIME_TO_CENTER = 50;
	
	private int offsetX = 0;
	private int offsetY = 0;
	
	private int timeCentering = 0;
	private boolean isCentering = false;
	private int centerStartX = -1;
	private int centerStartY = -1;
	private int centerToX = -1;
	private int centerToY = -1;
	private int width = 0;
	private int height = 0;
	
	Graphics2D g2d;
	
	//TEST ELEMENTS BELOW
	Random rand = new Random(); 
	
	private static final int NUM_TILES = 20; 
	int[][] tiles = new int[NUM_TILES][NUM_TILES];
	//END TEST ELEMENTS
	
	
	public GamePanel(Game game) {

		this.game = game;
		for (int i = 0; i < NUM_TILES; i++) {
			for(int j = 0; j < NUM_TILES; j++){
				tiles[i][j] = rand.nextInt(3);
			}
		}
	}
	
	public void draw(Graphics g, int width, int height) {
		g2d = (Graphics2D)g;
		this.width = width;
		this.height = height;
		/*
		if (centeringOffsetX(game.selectedThingX) != centerToX || 
				centeringOffsetY(game.selectedThingY) != centerToY) {
			centerOnTile(game.selectedThingX, game.selectedThingY);
		}
		*/
		if (isCentering)
			continueCentering();
		drawTiles();
		drawUnits();
		drawBases();
		drawSelectedItem();
	}

	private void drawSelectedItem() {
		if (game.getCurrentMode() == ModeEnum.UNIT) {
			//drawStaticElement(g, x, y, "UNIT_SELECTED");
		}
		if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
			//drawStaticElement(g, x, y, "BASE_SELECTED");
		}
		drawTiles();
	}

	private void drawBases() {
		// TODO Auto-generated method stub
		
	}

	private void drawUnits() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This function draws calls the functions
	 * that draw all the tiles
	 */
	private void drawTiles() {
		for (int i = 0; i < game.getGameBoard().gameMap.length; i++) {
			for (int j = 0; j < game.getGameBoard().gameMap[i].length; j++) {
				drawTile(i, j, game.getGameBoard().gameMap[i][j].getTileType());

			}
		}
	}
	
	private void drawBase(int x, int y, int player, int rotation) {
		switch(player) {
			case 0:
				drawStaticTileElement(x, y, "BASE_G");
				break;
			case 1:
				drawStaticTileElement(x, y, "BASE_B");
				break;
			case 2:
				drawStaticTileElement(x, y, "BASE_Y");
				break;
			case 3:
				drawStaticTileElement(x, y, "BASE_O");
				break;
			default:
				System.out.println("Invalid player specific for drawing base");
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
				drawStaticTileElement(x, y, "UNIT_G");
				break;
			case 1:
				drawStaticTileElement(x, y, "UNIT_B");
				break;
			case 2:
				drawStaticTileElement(x, y, "UNIT_Y");
				break;
			case 3:
				drawStaticTileElement(x, y, "UNIT_O");
				break;
			default:
				System.out.println("Invalid Player :" + player
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
			System.out.println("Invalid Unit Type :" + type 
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
