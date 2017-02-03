package src.view;

import java.awt.Graphics;
import game.Game;

public class UnitDetailsPanel extends DetailsPanel{
	private Game game;
	
	public UnitDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawBar(g, width, height);
	}

}