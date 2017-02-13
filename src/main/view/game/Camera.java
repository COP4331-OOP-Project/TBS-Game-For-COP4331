package view.game;

import game.Assets;

public class Camera {
	private PanelCenterer panelCenterer;
	private static final int HEX_W = Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
	private static final int HEX_H = (int)(HEX_W * 0.86);
	public Camera(GamePanel gamePanel) {
		this.panelCenterer = new PanelCenterer(gamePanel);
	}
	
	private int offsetX = 180;
	private int offsetY = -3050;
	
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
