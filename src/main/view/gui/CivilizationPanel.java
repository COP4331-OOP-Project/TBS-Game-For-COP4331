package view.gui;

import game.Assets;

import game.Assets;
import game.Game;
import game.Player;
import view.Panel;

import java.awt.Graphics;

import controls.command.CommandEnum;

import java.awt.Font;

public class CivilizationPanel extends Panel{
	Font civInfoFont = Assets.getInstance().getFont(2);
	private Game game;

	public CivilizationPanel(Game game) {
		this.game = game;
	}

	public static int GUI_PANEL_WIDTH =
			Assets.getInstance().getImage("GUI_TOP_LEFT").getWidth();
	public static int GUI_PANEL_HEIGHT =
			Assets.getInstance().getImage("GUI_TOP_LEFT").getHeight();

	public void draw(Graphics g, int screenWidth, int screenHeight) {
		drawBar(g, screenWidth, screenHeight);
		drawText(g);
		drawPlayerIcon(g);
	}



	private void drawPlayerIcon(Graphics g) {
		if (game.getCurrentPlayer().getPlayerID() == 0) {
			g.drawImage(Assets.getInstance().getImage("ICON_O"), 120, 9, null);
		} else {
			g.drawImage(Assets.getInstance().getImage("ICON_B"), 120, 9, null);
		}
		
	}

	private void drawText(Graphics g) {
		Font old = g.getFont();
		g.setFont(civInfoFont);
		g.drawString("Player: ", 10, 40);
		g.drawString("Turn: "+ game.getTurnNum(), 10, 80);
		g.setFont(old);
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
