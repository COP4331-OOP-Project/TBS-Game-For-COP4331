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
	
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				if(gBoard.gameMap[i][j].getTileType()==0) {
					g.drawImage(Assets.getInstance().getImage("TERRAIN_GRASS"), tileLocation(gBoard.gameMap[i][j].getlocation().xIndex),
							tileLocation(gBoard.gameMap[i][j].getlocation().yIndex), null);
				}
				if(gBoard.gameMap[i][j].getTileType()==1) {
					g.drawImage(Assets.getInstance().getImage("TERRAIN_SAND"), tileLocation(gBoard.gameMap[i][j].getlocation().xIndex),
							tileLocation(gBoard.gameMap[i][j].getlocation().yIndex), null);
				}
				if(gBoard.gameMap[i][j].getTileType()==2) {
					g.drawImage(Assets.getInstance().getImage("TERRAIN_WATER"), tileLocation(gBoard.gameMap[i][j].getlocation().xIndex),
							tileLocation(gBoard.gameMap[i][j].getlocation().yIndex), null);
				}

			}
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
