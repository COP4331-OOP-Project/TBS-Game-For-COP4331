package view.game;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.ModeEnum;
import controls.unit.UnitEnum;
import game.Game;
import game.entities.units.Unit;
import game.gameboard.Tile;

public class UnitDrawer {
	private final static Logger log = LogManager.getLogger(UnitDrawer.class);
	private GamePanel gamePanel;
	private Game game;
	public UnitDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	private void drawUnit(int x, int y, UnitEnum type, int player, 
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
	
	protected void drawUnits() {
		for (Tile[] tiles : this.game.getGameBoard().getTiles()) {
			for (Tile tile : tiles) {
				if (tile.containsUnit) {
					for (Unit unit : tile.getUnits()) {
						if (!tile.containsArmy && !tile.containsBattleGroup()) {
							drawUnit(tile.getLocation().getX(), tile.getLocation().getY(), unit.getUnitType(), unit.getOwnerID(), 0);
						}
					}
				}
			}
		}
	}
}
