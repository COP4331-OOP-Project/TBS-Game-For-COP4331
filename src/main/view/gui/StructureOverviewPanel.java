package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Assets;
import game.Game;

public class StructureOverviewPanel extends OverviewPanel{
	private Game game;
	public StructureOverviewPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawPanelBox(g, width, height);
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		g.setColor(Color.WHITE);
		g.setFont(Assets.getInstance().getFont(2));
		g.drawString("Structure Overview", width/2 - 370, height/2 - 245);
		g.setFont(Assets.getInstance().getFont(1));
		for (int i = 0; i < game.getCurrentPlayer().getBaseCount(); i++) {
			g.drawString("Base", width/2 - 370, height/2 + (i * 30) - 200);
		}
		g.setColor(oldColor);
		g.setFont(oldFont);
	}
}
