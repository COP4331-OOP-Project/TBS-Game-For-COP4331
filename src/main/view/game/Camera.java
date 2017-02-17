package view.game;

import game.Assets;

public class Camera {
    private static final int HEX_W = (int) Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
    private static final int HEX_H = (int) (HEX_W * 0.86);
    private PanelCenterer panelCenterer;
    private int offsetX = 180;
    private int offsetY = -3050;
    private double scale = 1;
	private static final double SCALE_AMOUNT = 0.02;
    public Camera(GamePanel gamePanel) {
        this.panelCenterer = new PanelCenterer(gamePanel);
    }

    protected int getX() {
        return offsetX;
    }

    protected void setX(int x) {
        offsetX = x;
    }

    protected int getY() {
        return offsetY;
    }
    
    protected double getScale() {
    	return scale;
    }

    protected void setY(int y) {
        offsetY = y;
    }

    protected int offsetX(int x, int y) {
        return getTileLocationX(x, y) + offsetX;
    }

    protected int offsetY(int x, int y) {
        return getTileLocationY(x, y) + offsetY;
    }
    
    protected int getTileLocationX(int x, int y) {
        return (int) (0.75f * scale * HEX_W * x);
    }

    protected int getTileLocationY(int x, int y) {
        return (int) ((y * (HEX_H * scale)) + ((x * 0.5f) * (HEX_H * scale)));
    }

    protected PanelCenterer getPanelCenterer() {
        return panelCenterer;
    }

	public void zoom(double deltaY, double mouseX, double mouseY) {
		if (deltaY > 0) {
			scale += SCALE_AMOUNT;
		} else {
			scale -= SCALE_AMOUNT;
		}
		
		//offsetX
	}

	public void doNotCenter() {
		panelCenterer.stopCentering();
	}
}
