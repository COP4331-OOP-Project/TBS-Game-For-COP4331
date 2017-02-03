package src.game;

import view.View;
import javax.swing.*;
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
		view.drawVisiblePanels(g, getSize().width, getSize().height);
	}

	public void updateAnimationTime() {
		view.updateAnimationTime();
	}
}
