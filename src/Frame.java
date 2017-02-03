import java.awt.Graphics;
import javax.swing.JPanel;

public class Frame extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public Frame(Game game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Paint Views Here
	}
}
