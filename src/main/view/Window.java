package view;
import java.awt.Dimension;
import java.awt.DisplayMode;

import javax.swing.JFrame;

import game.Game;

public class Window extends JFrame {
	private static final boolean FULLSCREEN_MODE = false;
	static final long serialVersionUID = 1L;
	private Frame frame;
	private int defaultScreenWidth = 1366;
	private int defaultScreenHeight = 768;
	private int screenWidth = defaultScreenWidth;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = defaultScreenHeight;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public int viewOffsetY = 0;
	public int viewOffsetX = 0;

	public Window(Game game) {
		frame = new Frame(game);
		getContentPane().add(frame);
		if (FULLSCREEN_MODE) {
			screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
			screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
			DisplayMode dm = new DisplayMode(screenWidth, screenHeight, 32, 60);
			setSize(new Dimension(dm.getWidth(), dm.getHeight()));
			setUndecorated(true);
		} else {
			setSize(screenWidth, screenHeight);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(screenWidth, screenHeight));
		}
		setVisible(true);
	}
	
	public void renderGame() {
		frame.repaint();
	}

	public void updateAnimationTime() {
		frame.updateAnimationTime();
	}
}