package view.game;

import java.awt.Color;
import java.awt.Font;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArmyDrawer {
	private final static Logger log = LogManager.getLogger(GamePanel.class);
	Font armyFont = new Font("Lucida Sans", Font.BOLD, 40);
	
	private GamePanel gamePanel;
	
	public ArmyDrawer(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	protected void drawArmy(int x, int y, int player, int rotation, 
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
					gamePanel.getTileSize()/2 - 15, gamePanel.getCamera().offsetY(x, y) + 
					gamePanel.getTileSize()/2 + 18);
			gamePanel.getG2D().setColor(Color.WHITE);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 17, gamePanel.getCamera().offsetY(x, y) + 
					gamePanel.getTileSize()/2 + 17);
		} else {
			gamePanel.getG2D().setColor(Color.BLACK);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 23, gamePanel.getCamera().offsetY(x, y) + 
					gamePanel.getTileSize()/2 + 18);
			gamePanel.getG2D().setColor(Color.WHITE);
			gamePanel.getG2D().drawString("" + numOfUnits, gamePanel.getCamera().offsetX(x, y) + 
					gamePanel.getTileSize()/2 - 25, gamePanel.getCamera().offsetY(x, y) + 
					gamePanel.getTileSize()/2 + 17);
		}
		gamePanel.getG2D().setColor(original);
	}
}
