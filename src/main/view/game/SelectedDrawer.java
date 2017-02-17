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

    public void drawSelectedItemOutline() {
        int x = game.getCenterCoordinates().getX();
        int y = game.getCenterCoordinates().getY();
        if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
            drawSelectedRallyPointOutline(x, y);
        } else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
            drawSelectedStructureOutline(x, y);
        } else if (game.getCurrentMode() == ModeEnum.UNIT) {
            drawSelectedUnitOutline(x, y);
        } else if (game.getCurrentMode() == ModeEnum.ARMY) {
            drawSelectedArmyOutline(x, y);
        }
    }

    private void drawSelectedRallyPointOutline(int x, int y) {
        if (game.getCurrentPlayer().getArmyRallyPoint().size() > 0)
            gamePanel.drawStaticTileElement(x, y, "RALLY_POINT_SELECTED");
    }

    private void drawSelectedStructureOutline(int x, int y) {
        if (game.getCurrentPlayer().getBases().size() > 0)
            gamePanel.drawStaticTileElement(x, y, "BASE_SELECTED");
    }

    private void drawSelectedArmyOutline(int x, int y) {
        if (game.getGameBoard().getTiles()[x][y].containsArmy) {
            gamePanel.drawStaticTileElement(x, y, "ARMY_SELECTED");
        }
    }

    private void drawSelectedUnitOutline(int x, int y) {
        if (game.getGameBoard().getTiles()[x][y].containsArmy) {
            gamePanel.drawStaticTileElement(x, y, "ARMY_SELECTED");
        } else if (game.getCurrentPlayer().getAllUnit().size() > 0) {
            gamePanel.drawStaticTileElement(x, y, "UNIT_SELECTED");
        }
    }


}
