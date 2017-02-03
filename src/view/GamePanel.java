package view;

import game.Assets;
import game.Game;
import game.Player;
import game.gameboard.GameBoard;

import java.awt.Graphics;
import java.util.Random;

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
	
	//TEST ELEMENTS BELOW
	Random rand = new Random(); 
	
	private static final int NUM_TILES = 5; 
	int[][] tiles = new int[NUM_TILES][NUM_TILES];
	//END TEST ELEMENTS
	
	
	public GamePanel(Game game) {

		gBoard = new GameBoard(1);

		this.game = game;
		for (int i = 0; i < NUM_TILES; i++) {
			for(int j = 0; j < NUM_TILES; j++){
				tiles[i][j] = rand.nextInt(3);
			}
		}
	}
	
	public void draw(Graphics g, int width, int height) {
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
		drawTiles(g);
	}

	/**
	 * This function draws calls the functions
	 * that draw all the tiles
	 */
	private void drawTiles(Graphics g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] == 0) {
					drawStaticTileElement(g, i, j, "TERRAIN_GRASS");
					drawUnit(g, i, j, 0, 2);
				} else if (tiles[i][j] == 1) {
					drawStaticTileElement(g, i, j, "TERRAIN_SAND");
					drawUnit(g, i, j, 1, 1);
				} else if (tiles[i][j] == 2) {
					drawAnimatedTileElement(g, i, j, "TERRAIN_WATER1", "TERRAIN_WATER2", "TERRAIN_WATER3");	
				}
			}
		}
	}
	
	private void drawUnit(Graphics g, int x, int y, int type, int player) {
		String typeString = "";
		switch (type) {
			case 0:
				typeString = "MELEE";
				break;
			case 1:
				typeString = "RANGED";
				break;
			case 2:
				typeString = "EXPLORER";
				break;
			case 3:
				typeString = "COLONIST";
				break;
			default:
				System.out.println("Invalid Unit Type :" + type 
						+ " cannot be drawn");
		}
		
		String playerString = "";
		switch (player) {
			case 0:
				playerString = "G"; //Green
				break;
			case 1:
				playerString = "B"; //Blue
				break;
			case 2:
				playerString = "Y"; //Yellow
				break;
			case 3:
				playerString = "O"; //Orange
				break;
			default:
				System.out.println("Invalid Player :" + player
						+ " cannot have units drawn");
		}
		String unitString = "UNIT_" + typeString + "_" + playerString;
		drawStaticTileElement(g, x, y, unitString);
	}
	
	private void drawStaticTileElement(Graphics g, int x, int y, String image) {
		g.drawImage(Assets.getInstance().getImage(image), offX(tileLocation(x)), 
				offY(tileLocation(y)), null); 
	}
	
	private void drawAnimatedTileElement(Graphics g, int x, int y, 
			String image1, String image2, String image3) {
		switch (getAnimationImage()) {
		case 0:
			g.drawImage(Assets.getInstance().getImage(image1), offX(tileLocation(x)), offY(tileLocation(y)), null);
			break;
		case 2:
			g.drawImage(Assets.getInstance().getImage(image2), offX(tileLocation(x)), offY(tileLocation(y)), null);
			break;
		default:
			g.drawImage(Assets.getInstance().getImage(image3), offX(tileLocation(x)), offY(tileLocation(y)), null);
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
	
}
