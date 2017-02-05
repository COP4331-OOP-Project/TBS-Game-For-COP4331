package view;

import java.awt.Graphics;

import game.Assets;
import game.Game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiniPanel extends Panel{
	Game game;
	private static final int TILE_SIZE = 6;
	private static final int DISTANCE_FROM_RIGHT = 170;
	private final static Logger log = LogManager.getLogger(MiniPanel.class);
	
	private int width;
	private int height;
	
	public MiniPanel(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g, int width, int height) {
		this.width = width;
		this.height = height;
		drawCorners(g, game.getGameBoard().gameMap.length);
		for (int i = 0; i < game.getGameBoard().gameMap.length; i++) {
			drawTopOutline(g, i, 0);
			drawLeftOutline(g, 0, i);
			for (int j = 0; j < game.getGameBoard().gameMap[i].length; j++) {
				
				drawSmallTile(g, i, j, game.getGameBoard().gameMap[j][i].getTileType());
				
			}
			drawRightOutline(g, game.getGameBoard().gameMap.length - 1, i);
			drawBottomOutline(g, i, game.getGameBoard().gameMap.length - 1);
		}
	}

	private void drawCorners(Graphics g, int length) {
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(0) - TILE_SIZE, offY(0) - TILE_SIZE, null);
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(0) - TILE_SIZE, offY(length), null);
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(length), offY(0) - TILE_SIZE, null);
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(length), offY(length), null);//Good
		
	}

	private void drawLeftOutline(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(x) - TILE_SIZE, offY(y), null); 
		
	}

	private void drawRightOutline(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(x) + TILE_SIZE, offY(y), null); 
		
	}

	private void drawTopOutline(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(x), offY(y) - TILE_SIZE, null); 
	}
	
	private void drawBottomOutline(Graphics g, int x, int y) {
		g.drawImage(Assets.getInstance().getImage("OUTLINE_MINI"), 
				offX(x), offY(y) + TILE_SIZE, null); 
	}

	private void drawSmallTile(Graphics g, int x, int y, int tileType) {
		switch (tileType) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GRASS_MINI"), 
						offX(x), offY(y), null); 
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("SAND_MINI"), 
						offX(x), offY(y), null); 
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("WATER_MINI"), 
						offX(x), offY(y), null); 
				break;
			default:
				log.warn("Invalid tile type");
		}
	}
	
	private int offX(int x) {
		return this.width + (TILE_SIZE * x) - 
				DISTANCE_FROM_RIGHT ;
	}
	
	private int offY(int y) {
		return (this.height / 2) + (y * TILE_SIZE);
	}

}
