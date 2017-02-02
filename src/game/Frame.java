package game;

import view.View;

import javax.swing.*;
import java.awt.*;

public class Frame extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	private View view;
	
	public Frame(Game game) {
		this.game = game;
		view = new View(game);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		view.drawVisiblePanels(g, getSize().width, getSize().height);
	}
}
