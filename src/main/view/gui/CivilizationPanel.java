package view.gui;

import game.Assets;
import game.Game;
import view.Panel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;


public class CivilizationPanel extends Panel{
	Font civInfoFont = Assets.getInstance().getFont(2);
	private Game game;

	public CivilizationPanel(Game game) {
		this.game = game;
	}

	public static int GUI_PANEL_WIDTH =
			(int) Assets.getInstance().getImage("GUI_TOP_LEFT").getWidth();
	public static int GUI_PANEL_HEIGHT =
			(int) Assets.getInstance().getImage("GUI_TOP_LEFT").getHeight();

	public void draw(GraphicsContext gc, int screenWidth, int screenHeight) {
		drawBar(gc, screenWidth, screenHeight);
		drawText(gc);
		drawPlayerIcon(gc);
	}



	private void drawPlayerIcon(GraphicsContext g) {
		if (game.getCurrentPlayer().getPlayerID() == 0) {
			g.drawImage(Assets.getInstance().getImage("ICON_O"), 120, 9);
		} else {
			g.drawImage(Assets.getInstance().getImage("ICON_B"), 120, 9);
		}
		
	}

	private void drawText(GraphicsContext g) {
		Font old = g.getFont();
		g.setFont(civInfoFont);
		g.fillText("Player: ", 10, 40);
		g.fillText("Turn: "+ game.getTurnNum(), 10, 80);
		g.setFont(old);
	}

	//Draw the blue panel itself
	private void drawBar(GraphicsContext g, int screenWidth, int screenHeight) {
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_LEFT"), 0, 0);
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_RIGHT"),
				screenWidth - GUI_PANEL_WIDTH, 0);
		int distanceFromRight = screenWidth - GUI_PANEL_WIDTH;
		for(int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
			g.drawImage(Assets.getInstance().getImage("GUI_TOP_MIDDLE"), i, 0);
		}
	}
}
