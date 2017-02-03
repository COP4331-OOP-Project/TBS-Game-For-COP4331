package view;

import java.awt.Graphics;

import game.Assets;

public class ControlModePanel extends Panel{
	private static final int PANEL_HEIGHT = 114;
	private static final int PANEL_DISTANCE_BETWEEN = 40;
	private static final int PANEL_DISTANCE_FROM_LEFT = 5;
	int mode = 0;
	int submode = 0;
	int width;
	int height;
	
	public void draw(Graphics g, int width, int height) {
		this.width = width;
		this.height = height;
		drawModePanel(g);
		drawSubmodePanel(g);
	}

	private void drawModePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
				, height/2 - PANEL_DISTANCE_BETWEEN - PANEL_HEIGHT, null);
		switch(mode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_BETWEEN - PANEL_HEIGHT, null);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_BETWEEN - PANEL_HEIGHT, null);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_BETWEEN - PANEL_HEIGHT, null);
				break;
			default:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_BETWEEN - PANEL_HEIGHT, null);
		}
				
	}
	
	private void drawSubmodePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT,
				height/2 + PANEL_DISTANCE_BETWEEN, null);
		switch(submode) {
		case 0:
			g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
					, height/2 + PANEL_DISTANCE_BETWEEN, null);
			break;
		case 1:
			g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
					, height/2 + PANEL_DISTANCE_BETWEEN, null);
			break;
		case 2:
			g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
					, height/2 + PANEL_DISTANCE_BETWEEN, null);
			break;
		default:
			g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
					, height/2 + PANEL_DISTANCE_BETWEEN, null);
	}
		
	}
}
