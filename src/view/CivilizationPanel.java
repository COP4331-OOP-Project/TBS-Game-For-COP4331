package view;

import game.Assets;
import java.awt.Graphics;
import java.awt.Font;

public class CivilizationPanel extends Panel{
	
	Font civInfoFont = new Font("Lucida Sans", Font.BOLD, 20);
	
	public CivilizationPanel() {
		
	}
	
	public static int GUI_PANEL_WIDTH = 
			Assets.getInstance().getImage("GUI_TOP_LEFT").getWidth();
	public static int GUI_PANEL_HEIGHT = 
			Assets.getInstance().getImage("GUI_TOP_LEFT").getHeight();
	
	public void draw(Graphics g, int screenWidth, int screenHeight) {
		drawBar(g, screenWidth, screenHeight);
		drawText(g);
	}

	private void drawText(Graphics g) {
		g.setFont(civInfoFont);
		g.drawString("Player: ", 150, 20);
		g.drawString("Turn: ", 150, 45);
	}

	//Draw the blue panel itself
	private void drawBar(Graphics g, int screenWidth, int screenHeight) {
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_LEFT"), 0, 0, null);
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_RIGHT"), 
				screenWidth - GUI_PANEL_WIDTH, 0, null);
		int distanceFromRight = screenWidth - GUI_PANEL_WIDTH;
		for(int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
			g.drawImage(Assets.getInstance().getImage("GUI_TOP_MIDDLE"), i, 0, null); 
		}
	}
}
