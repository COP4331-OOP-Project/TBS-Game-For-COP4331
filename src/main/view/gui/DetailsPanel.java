package view.gui;

import java.awt.Graphics;

import game.Assets;
import view.Panel;

public abstract class DetailsPanel extends Panel{
	private static int GUI_PANEL_WIDTH = 
			Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getWidth();
	private static int GUI_PANEL_HEIGHT = 
			Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getHeight();

	void drawBar(Graphics g, int width, int height) {
		g.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_LEFT"), 0, height - GUI_PANEL_HEIGHT, null);
		g.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_RIGHT"), 
				width - GUI_PANEL_WIDTH, height - GUI_PANEL_HEIGHT, null);
		int distanceFromRight = width - GUI_PANEL_WIDTH;
		for(int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
			g.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_MIDDLE"), i, height - GUI_PANEL_HEIGHT, null); 
		}
	}

}
