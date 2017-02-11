package view.gui;

import java.awt.Graphics;

import game.Assets;
import game.Game;
import view.Panel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiniMapPanel extends Panel{
	Game game;
	private static final int HEX_SIZE = 12;
	private static final int HEX_HEIGHT = 10;
	private static final int DISTANCE_FROM_RIGHT = 420;
	private static final int DISTANCE_FROM_BOTTOM = 550;
	private final static Logger log = LogManager.getLogger(MiniMapPanel.class);
	
	private int width;
	private int height;
	
	public MiniMapPanel(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g, int width, int height) {
		this.width = width;
		this.height = height;
		drawPanel(g);
		for (int i = 0; i < game.getGameBoard().gameMap.length; i++) {
			for (int j = 0; j < game.getGameBoard().gameMap[i].length; j++) {
				
				drawSmallTile(g, i, j, game.getGameBoard().gameMap[i][j].getTileType());				
			}
		}
		drawBorder(g);
	}

	private void drawBorder(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MINI_MAP_BORDER"), width - DISTANCE_FROM_RIGHT - 27
                , height-DISTANCE_FROM_BOTTOM + 182, null);
		
	}

	private void drawPanel(Graphics g) {
        g.drawImage(Assets.getInstance().getImage("GUI_MINI_MAP_PANEL"), width - DISTANCE_FROM_RIGHT - 50
                , height-DISTANCE_FROM_BOTTOM + 163, null);
	}

	private void drawSmallTile(Graphics g, int x, int y, int tileType) {
		switch (tileType) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GRASS_MINI"), 
						offX(x, y), offY(x, y), null); 
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("SAND_MINI"), 
						offX(x, y), offY(x, y), null); 
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("WATER_MINI"), 
						offX(x, y), offY(x, y), null); 
				break;
			default:
				log.warn("Invalid tile type");
		}
	}
	
	private int offX(int x, int y) {
		return this.width + ((int)(((x * 0.5f) * HEX_SIZE/2) + (x * 0.5f) * HEX_SIZE)) - 
				DISTANCE_FROM_RIGHT;
	}
	
	private int offY(int x, int y) {
		return ((int)((y * HEX_HEIGHT) + ((x * 0.5f) * HEX_HEIGHT))) + this.height - 
				DISTANCE_FROM_BOTTOM;
	}
}
