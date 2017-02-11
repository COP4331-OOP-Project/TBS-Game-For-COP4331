package view.game;

import game.Game;

public class Camera {
	private PanelCenterer panelCenterer;
	private static final int HEX_W = 100;
	private static final int HEX_H = 86;
	public Camera(GamePanel gamePanel, Game game) {
		this.panelCenterer = new PanelCenterer(gamePanel);
	}
	
	private int offsetX = 180;
	private int offsetY = -2340;
	
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
	
	protected int offsetY(int x, int y) {
		return getTileLocationY(x, y) + offsetY;
	}
	
	protected int getTileLocationX(int x, int y) {
		return (int)(((x * 0.5f) * HEX_W/2) + (x * 0.5f) * HEX_W);
	}
	
	protected int getTileLocationY(int x, int y) {
		return (int)((y * HEX_H) + ((x * 0.5f) * HEX_H));
	}
	
	protected PanelCenterer getPanelCenterer() {
		return panelCenterer;
	}
}
