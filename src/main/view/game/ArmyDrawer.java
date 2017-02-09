package view.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.Game;
import game.entities.Army;
import game.gameboard.Tile;

public class ArmyDrawer {
	private final static Logger log = LogManager.getLogger(GamePanel.class);
	Font armyFont = new Font("Lucida Sans", Font.BOLD, 40);
	
	private GamePanel gamePanel;
	private Game game;
	
	public ArmyDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	protected void drawArmies() {
		for (Tile[] tiles : this.game.getGameBoard().getTiles()) {
			for (Tile tile : tiles) {
				if (tile.containsArmy) {
					for (Army army : tile.getArmies()) {
						drawArmy(tile.getLocation().getX(), tile.getLocation().getY(), army.getOwnerID(), 
								army.getRotation(), army.getAllUnits().size());
					}
				} else if (tile.containsBattleGroup() && tile.getUnits().size() > 0) {
					// this is so wrong but might work for demo
					
					drawArmy(tile.getLocation().getX(), tile.getLocation().getY(),
							tile.getUnits().get(0).getOwnerID(), 0, tile.getUnits().size()); // lol
				}
			}
		}
		
	}
	
	private void drawArmy(int x, int y, int player, int rotation, 
			int numOfUnits) {
		switch (player) {
			case 0:
				gamePanel.drawStaticTileElement(x, y, rotation, "ARMY_O");
				break;
			case 1:
				gamePanel.drawStaticTileElement(x, y, rotation, "ARMY_B");
				break;
			case 2:
				gamePanel.drawStaticTileElement(x, y, rotation, "ARMY_Y");
				break;
			case 3:
				gamePanel.drawStaticTileElement(x, y, rotation, "ARMY_G");
				break;
			default:
				log.warn("Invalid Player :" + player
						+ " cannot have units drawn");
		}
		gamePanel.getG2D().setFont(armyFont);
		Color original = gamePanel.getG2D().getColor();
		
		if (numOfUnits < 10) {
			gamePanel.getG2D().setColor(Color.BLACK);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x , y) + 
					gamePanel.getTileSize()/2 - 15, gamePanel.getCamera().offsetY(y) + 
					gamePanel.getTileSize()/2 + 18);
			gamePanel.getG2D().setColor(Color.WHITE);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 17, gamePanel.getCamera().offsetY(y) + 
					gamePanel.getTileSize()/2 + 17);
		} else {
			gamePanel.getG2D().setColor(Color.BLACK);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 23, gamePanel.getCamera().offsetY(y) + 
					gamePanel.getTileSize()/2 + 18);
			gamePanel.getG2D().setColor(Color.WHITE);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 25, gamePanel.getCamera().offsetY(y) + 
					gamePanel.getTileSize()/2 + 17);
		}
		gamePanel.getG2D().setColor(original);
	}
}
