package view;

import game.Assets;
import game.Game;
import game.Player;
import game.gameboard.GameBoard;

import java.awt.Graphics;
import java.util.Random;

public class GamePanel extends Panel {
	Game game;

	GameBoard gBoard;

	Random rand = new Random(); //DELETE THIS LATER
	private static int TILE_PIXEL_SIZE = 100;
	private static int NUM_TILES = 5;
	int[][] tiles = new int[NUM_TILES][NUM_TILES];
	
	
	public GamePanel(Game game) {

		gBoard = new GameBoard(1);

		this.game = game;
		for(int i = 0; i < NUM_TILES; i++) {
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
					drawGrassTile(g, i, j);
				} else if (tiles[i][j] == 1) {
					drawSandTile(g, i, j);
				} else if (tiles[i][j] == 2) {
					drawWaterTile(g, i, j);	
				}
			}
		}
	}
		
	private void drawWaterTile(Graphics g, int x, int y) {
		switch (getAnimationImage()) {
		case 0:
			g.drawImage(Assets.getInstance().getImage("TERRAIN_WATER1"), tileLocation(x), tileLocation(y), null);
			break;
		case 2:
			g.drawImage(Assets.getInstance().getImage("TERRAIN_WATER3"), tileLocation(x), tileLocation(y), null);
			break;
		default:
			g.drawImage(Assets.getInstance().getImage("TERRAIN_WATER2"), tileLocation(x), tileLocation(y), null);
	
		}
	}
	
	private void drawSandTile(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("TERRAIN_SAND"), tileLocation(x), tileLocation(y), null); 
	}
	
	private void drawGrassTile(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("TERRAIN_GRASS"), tileLocation(x), tileLocation(y), null); 
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
