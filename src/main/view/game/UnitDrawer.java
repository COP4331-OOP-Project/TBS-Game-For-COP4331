package view.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.unit.UnitEnum;

public class UnitDrawer {
	private final static Logger log = LogManager.getLogger(UnitDrawer.class);
	private GamePanel gamePanel;
	public UnitDrawer(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	protected void drawUnit(int x, int y, UnitEnum type, int player, 
			int rotation) {
		switch (player) {
			case 0:
				gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_O");
				break;
			case 1:
				gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_B");
				break;
			case 2:
				gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_Y");
				break;
			case 3:
				gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_G");
				break;
			default:
				log.warn("Invalid Player :" + player
						+ " cannot have units drawn");
		}
		
		switch (type) {
		case MELEE:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_MELEE");
			break;
		case RANGED:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_RANGED");
			break;
		case EXPLORER:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_EXPLORER");
			break;
		case COLONIST:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_COLONIST");
			break;
		default:
			log.warn("Invalid unit Type :" + type
					+ " cannot be drawn");
		}
	}
}
