package view;

import javax.swing.*;

import game.Game;

import java.awt.*;

public class Frame extends JPanel{
	private static final long serialVersionUID = 1L;
	private View view;


	public Frame(Game game) {
		view = new View(game);
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		view.drawVisiblePanels((Graphics2D)g, getSize().width, getSize().height);
	}

	public void updateAnimationTime() {
		view.updateAnimationTime();
	}
}
