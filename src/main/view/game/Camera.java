package view.game;

import game.Game;

public class Camera {
	private GamePanel gamePanel;
	private PanelCenterer panelCenterer;
	
	public Camera(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.panelCenterer = new PanelCenterer(game, gamePanel);
	}
	
	private int offsetX = 0;
	private int offsetY = 0;
	
	protected void setX(int x) {
		offsetX = x;
	}
	
	protected void setY(int y) {
		offsetY = y;
	}
	
	protected int getX() {
		return offsetX;
	}
	
	protected int getY() {
		return offsetY;
	}
	
	protected int offsetX(int x) {
		return getTileLocation(x + offsetX);
	}
	
	protected int offsetY(int y) {
		return getTileLocation(y + offsetY);
	}
	
	protected int getTileLocation(int value) {
		return value * gamePanel.getTileSize();
	}
	
	protected PanelCenterer getPanelCenterer() {
		return panelCenterer;
	}
}
