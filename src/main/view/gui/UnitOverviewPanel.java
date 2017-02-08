package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controls.unit.UnitEnum;
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
		g.setFont(titleFont);
		g.drawString("Unit Overview", width/2 - 370, height/2 - 245);
		for (int i = 0; i < game.getCurrentPlayer().getAllUnit().size(); i++) {
			String unitString = "";
			if (game.getCurrentPlayer().getAllUnit().get(i).getUnitType() == 0) {
				unitString = "Melee";
			}
			if (game.getCurrentPlayer().getAllUnit().get(i).getUnitType() == 1) {
				unitString = "Ranged";
			}
			if (game.getCurrentPlayer().getAllUnit().get(i).getUnitType() == 2) {
				unitString = "Explorer";
			}
			if (game.getCurrentPlayer().getAllUnit().get(i).getUnitType() == 3) {
				unitString = "Colonist";
			}
			if (game.getSelectedUnit() == i) {
				g.setFont(boldFont);
			} else {
				g.setFont(plainFont);
			}
			g.drawString(unitString, width/2 - 370, height/2 + (i * 30) - 200);
		}
		g.setColor(oldColor);
		g.setFont(oldFont);
	}

}
