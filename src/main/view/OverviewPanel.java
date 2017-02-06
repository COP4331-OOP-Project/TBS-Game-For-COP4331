package view;

import java.awt.Graphics;

import game.Assets;

public abstract class OverviewPanel extends Panel{
	public void drawPanelBox(Graphics g, int width, int height) {
		g.drawImage(Assets.getInstance().getImage("DETAILS_PANEL"),
				width/2 - 400, height/2 - 300, null);
	}
}
