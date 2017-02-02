package view;

import game.Assets;
import java.awt.Graphics;

public class CivilizationPanel extends Panel{
	
	public CivilizationPanel() {
		
	}
	
	public static int GUI_PANEL_WIDTH = 
			Assets.getInstance().getImage("GUI_TOP_LEFT").getWidth();
	public static int GUI_PANEL_HEIGHT = 
			Assets.getInstance().getImage("GUI_TOP_LEFT").getHeight();
	
	public void draw(Graphics g, int screenWidth, int screenHeight) {
		//Draw the blue panel itself
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_LEFT"), 0, 0, null);
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_RIGHT"), 
				screenWidth - GUI_PANEL_WIDTH, 0, null);
		int distanceFromRight = screenWidth - GUI_PANEL_WIDTH;
		for(int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
			g.drawImage(Assets.getInstance().getImage("GUI_TOP_MIDDLE"), i, 0, null); 
		}
	}

	@Override
	public void draw(Graphics g) {
	}
}
