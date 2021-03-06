package view.gui.game;

import controls.unit.UnitEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Point;

public class UnitOverviewPanel extends OverviewPanel {
    private Game game;

    public UnitOverviewPanel(Game game) {
    	this.setIsVisible(false);
        this.game = game;
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawPanelBox(gc, screenDimensions);
        Font oldFont = gc.getFont();
        gc.setFont(Assets.getInstance().getFont(2));
        gc.fillText("Unit Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        for (int i = 0; i < game.getCurrentPlayer().getAllUnit().size(); i++) {
            String unitString = "";
            UnitEnum unit = game.getCurrentPlayer().getAllUnit().get(i).getUnitType();
            if (unit == UnitEnum.MELEE) {
                unitString = "Melee";
            }
            if (unit == UnitEnum.RANGED) {
                unitString = "Ranged";
            }
            if (unit == UnitEnum.EXPLORER) {
                unitString = "Explorer";
            }
            if (unit == UnitEnum.COLONIST) {
                unitString = "Colonist";
            }
            //if (game.getSelectedUnit() == i) {
            //	gc.setFont(Assets.getInstance().getFont(1).deriveFont(Font.BOLD));
            //} else {
            gc.setFont(Assets.getInstance().getFont(1));
            //}
            gc.fillText(unitString, screenDimensions.x / 2 - 370, screenDimensions.y / 2 + (i * 30) - 200);
        }
        gc.setFont(oldFont);
    }

	@Override
	public void hideGUIElements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showGUIElements() {
		// TODO Auto-generated method stub
		
	}

}
