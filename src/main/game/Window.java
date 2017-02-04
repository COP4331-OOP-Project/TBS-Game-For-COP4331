package game;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;
	private Frame frame;
	private int screenWidth = 800;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = 600;
			//java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public int viewOffsetY = 0;
	public int viewOffsetX = 0;

	public Window(Game game, EventController events) {
		frame = new Frame(game);
		addKeyListener(events);
		getContentPane().add(frame);
		setLocationRelativeTo(null);//Centers Window
		setSize(screenWidth, screenHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	public void renderGame() {
		frame.repaint();
	}

	public void updateAnimationTime() {
		frame.updateAnimationTime();
	}
}
