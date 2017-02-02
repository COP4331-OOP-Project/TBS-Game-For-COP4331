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
	
	//TEST ELEMENTS BELOW
	Random rand = new Random(); 
	private static final int NUM_TILES = 50; 
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
	
	public void draw(Graphics g) {
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
					drawUnit(g, i, j, 0, rand.nextInt(4));
				} else if (tiles[i][j] == 1) {
					drawStaticTileElement(g, i, j, "TERRAIN_SAND");
					drawUnit(g, i, j, 1, rand.nextInt(4));
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
		g.drawImage(Assets.getInstance().getImage(unitString), tileLocation(x), tileLocation(y), null); 
	}
	
	private void drawStaticTileElement(Graphics g, int x, int y, String image) {
		g.drawImage(Assets.getInstance().getImage(image), tileLocation(x), tileLocation(y), null); 

	}
	
	
	private void drawAnimatedTileElement(Graphics g, int x, int y, 
			String image1, String image2, String image3) {
		switch (getAnimationImage()) {
		case 0:
			g.drawImage(Assets.getInstance().getImage(image1), tileLocation(x), tileLocation(y), null);
			break;
		case 2:
			g.drawImage(Assets.getInstance().getImage(image2), tileLocation(x), tileLocation(y), null);
			break;
		default:
			g.drawImage(Assets.getInstance().getImage(image3), tileLocation(x), tileLocation(y), null);
	
		}
	}

	/**
	 * This takes the location of the tile
	 * in the array, and converts the value
	 * to a pixel location in which it will be displayed
	 */
	private int tileLocation(int value) {
		return value * TILE_PIXEL_SIZE;
	}

}
