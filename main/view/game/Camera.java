package view.game;

import game.Game;

public class Camera {
	private GamePanel gamePanel;
	private PanelCenterer panelCenterer;
	private static final int HEX_W = 100;
	private static final int HEX_H = 86;
	public Camera(GamePanel gamePanel, Game game) {
		this.gamePanel = gamePanel;
		this.panelCenterer = new PanelCenterer(gamePanel);
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
	
	protected int offsetX(int x, int y) {
		return getTileLocationX(x, y) + offsetX;
	}
	
	protected int offsetY(int y) {
		return getTileLocationY(y) + offsetY;
	}
	
	protected int getTileLocationX(int x, int y) {
		return (int)((x * HEX_W) + ((y * 0.5f) * HEX_W));
	}
	
	protected int getTileLocationY(int y) {
		return (int)(((y * 0.5f) * HEX_H/2) + (y * 0.5f) * HEX_H);
	}
	
	protected PanelCenterer getPanelCenterer() {
		return panelCenterer;
	}
}
