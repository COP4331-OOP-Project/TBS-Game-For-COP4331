package view;

import java.awt.Graphics;

import game.Game;

public class StructureDetailsPanel extends DetailsPanel{
private Game game;

	public StructureDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawBar(g, width, height);
	}

}
