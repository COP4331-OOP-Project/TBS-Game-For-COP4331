package view;

import java.awt.Font;
import java.awt.Graphics;

import game.Assets;

public abstract class OverviewPanel extends Panel{
	Font titleFont = new Font("Lucida Sans", Font.BOLD, 35);
	Font plainFont = new Font("Lucida Sans", Font.PLAIN, 20);
	Font boldFont = new Font("Lucida Sans", Font.BOLD, 20);
	
	public void drawPanelBox(Graphics g, int width, int height) {
		g.drawImage(Assets.getInstance().getImage("DETAILS_PANEL"),
				width/2 - 400, height/2 - 300, null);
	}
}
