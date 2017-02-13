package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controls.unit.UnitEnum;
import game.Assets;
import game.Game;

public class UnitOverviewPanel extends OverviewPanel{
	private Game game;
	
	public UnitOverviewPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawPanelBox(g, width, height);
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		g.setColor(Color.WHITE);
		g.setFont(Assets.getInstance().getFont(2));
		g.drawString("Unit Overview", width/2 - 370, height/2 - 245);
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
			if (game.getSelectedUnit() == i) {
				g.setFont(Assets.getInstance().getFont(1).deriveFont(Font.BOLD));
			} else {
				g.setFont(Assets.getInstance().getFont(1));
			}
			g.drawString(unitString, width/2 - 370, height/2 + (i * 30) - 200);
		}
		g.setColor(oldColor);
		g.setFont(oldFont);
	}

}
