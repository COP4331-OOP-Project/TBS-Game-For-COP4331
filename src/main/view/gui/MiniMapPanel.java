package view.gui;

import java.awt.Graphics;

import game.Assets;
import game.Game;
import game.entities.units.Unit;
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
		for (int i = 0; i < game.getGameBoard().getTiles().length; i++) {
			for (int j = 0; j < game.getGameBoard().getTiles()[i].length; j++) {
				drawSmallTile(g, i, j, game.getGameBoard().getTiles()[i][j].getTileType());

				if (game.getGameBoard().getTiles()[i][j].containsStructure) {
					drawSmallStructure(game.getGameBoard().getTiles()[i][j].getStructure().getLocation().getX(), 
							game.getGameBoard().getTiles()[i][j].getStructure().getLocation().getY(), 
							game.getGameBoard().getTiles()[i][j].getStructure().getOwnerID(), g);
				}
				if (game.getGameBoard().getTiles()[i][j].containsUnit) {
					for (Unit unit : game.getGameBoard().getTiles()[i][j].getUnits()) {
						if (!game.getGameBoard().getTiles()[i][j].containsArmy && !game.getGameBoard().getTiles()[i][j].containsBattleGroup()) {
							drawSmallUnit(game.getGameBoard().getTiles()[i][j].getLocation().getX(), 
									game.getGameBoard().getTiles()[i][j].getLocation().getY(), 
									unit.getUnitType(), unit.getOwnerID(), g);
						}
					}
				}
			}
		}
		drawBorder(g);
	}

	private void drawSmallStructure(int x, int y, int ownerID, Graphics g) {
		if (ownerID == 0) {
			g.drawImage(Assets.getInstance().getImage("BASE_O_SMALL"), offX(x, y), offY(x, y), null);
			
			} else {
				g.drawImage(Assets.getInstance().getImage("BASE_B_SMALL"), offX(x, y), offY(x, y), null);
			}
	}

	private void drawSmallUnit(int x, int y, int unitType, int ownerID, Graphics g) {
		if (ownerID == 0) {
		g.drawImage(Assets.getInstance().getImage("UNIT_O_SMALL"), offX(x, y), offY(x, y), null);
		
		} else {
			g.drawImage(Assets.getInstance().getImage("UNIT_B_SMALL"), offX(x, y), offY(x, y), null);
		}
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
