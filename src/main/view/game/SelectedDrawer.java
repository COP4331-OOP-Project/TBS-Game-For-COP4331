package view.game;

import controls.ModeEnum;
import game.Game;

public class SelectedDrawer {
	Game game;
	GamePanel gamePanel;
	
	public SelectedDrawer(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
	}
	
	public void drawSelectedItem(boolean isArmyUnit) {
		if (!(gamePanel.getSelectedX() == -1 &&
				gamePanel.getSelectedY() == -1)) {
			int x = gamePanel.getSelectedX();
			int y = gamePanel.getSelectedY();
			if (game.getCurrentMode() == ModeEnum.RALLY_POINT
					&& game.getCurrentPlayer().getArmyRallyPoint().size() > 0) {
				gamePanel.drawStaticTileElement(x, y, "RALLY_POINT_SELECTED");
			}
			if (game.getCurrentMode() == ModeEnum.UNIT) {
				if (isArmyUnit) {
					gamePanel.drawStaticTileElement(x, y, "ARMY_SELECTED");
				} else {
					gamePanel.drawStaticTileElement(x, y, "UNIT_SELECTED");
				}
				
			}
			if (game.getCurrentMode() == ModeEnum.STRUCTURE
					&& game.getCurrentPlayer().getBases().size() > 0) {
				gamePanel.drawStaticTileElement(x, y, "BASE_SELECTED");
			}
			if (game.getCurrentMode() == ModeEnum.ARMY) {
				gamePanel.drawStaticTileElement(x, y, "UNIT_SELECTED");
			}
		}
	}
}
