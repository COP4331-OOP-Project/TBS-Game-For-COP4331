package view.game;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.ModeEnum;
import controls.unit.UnitEnum;
import game.Game;
import game.entities.units.Unit;
import game.gameboard.Tile;

public class UnitDrawer {
	private final static Logger log = LogManager.getLogger(GamePanel.class);
	private GamePanel gamePanel;
	private Game game;
	public UnitDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	private void drawUnit(int x, int y, int type, int player, 
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
		case 0:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_MELEE");
			break;
		case 1:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_RANGED");
			break;
		case 2:
			gamePanel.drawStaticTileElement(x, y, rotation, "UNIT_EXPLORER");
			break;
		case 3:
			gamePanel.drawStaticTileElement(x, y ,rotation, "UNIT_COLONIST");
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
				
		int unitSelected = -1;
		for (int player = 0; player < game.getAllPlayers().size(); player++) {
			for (int i = 0; i < game.getPlayer(player).getAllUnit().size(); i++) {
				if (game.getPlayer(player).getAllUnit().get(i).getLocation().getX() == 
						gamePanel.getCenterer().getSelectedX()
						&& game.getPlayer(player).getAllUnit().get(i).getLocation().getY() == 
						gamePanel.getCenterer().getSelectedY()
						&& gamePanel.getCenterer().getSelectedX() != -1 && 
								gamePanel.getCenterer().getSelectedY() != -1) {
					if (game.getPlayer(player).getAllUnit().get(i).getUnitType() == 0 &&
							game.getCurrentType() == UnitEnum.MELEE)
						unitSelected = i;
					if (game.getPlayer(player).getAllUnit().get(i).getUnitType() == 1 &&
							game.getCurrentType() == UnitEnum.RANGED)
						unitSelected = i;
					if (game.getPlayer(player).getAllUnit().get(i).getUnitType() == 2 &&
							game.getCurrentType() == UnitEnum.EXPLORER)
						unitSelected = i;
					if (game.getPlayer(player).getAllUnit().get(i).getUnitType() == 3 &&
							game.getCurrentType() == UnitEnum.COLONIST)
						unitSelected = i;
				}

			}
			if (game.getCurrentMode() == ModeEnum.UNIT && unitSelected != -1
				&& game.getCurrentPlayer().getPlayerID() == player &&
				!(game.getGameBoard().gameMap[game.getPlayer(player).getAllUnit().get(unitSelected).getLocation().getX()]
						[game.getPlayer(player).getAllUnit().get(unitSelected).getLocation().getY()]).containsArmy) {
				game.setSelectedUnit(unitSelected);
				gamePanel.drawSelectedItem(false);
			} else if (game.getCurrentMode() == ModeEnum.UNIT && unitSelected != -1
				&& game.getCurrentPlayer().getPlayerID() == player &&
				(game.getGameBoard().gameMap[game.getPlayer(player).getAllUnit().get(unitSelected).getLocation().getX()]
						[game.getPlayer(player).getAllUnit().get(unitSelected).getLocation().getY()]).containsArmy) {
				game.setSelectedUnit(unitSelected);
				gamePanel.drawSelectedItem(true);
			} else if (game.getCurrentPlayer().getPlayerID() == player){
				unitSelected = -1;
				game.setSelectedUnit(-1);
			}
		}
	}
}