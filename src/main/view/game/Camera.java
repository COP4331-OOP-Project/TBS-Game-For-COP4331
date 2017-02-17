package view.game;

import game.Assets;
import view.Point;

public class Camera {
    private static final int HEX_W = (int) Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
    private static final int HEX_H = (int) (HEX_W * 0.86);
    private PanelCenterer panelCenterer;
    private Point offset = new Point(180, -3050);
    private double scale = 1;
	private static final double SCALE_AMOUNT = 0.04;
    public Camera(GamePanel gamePanel) {
        this.panelCenterer = new PanelCenterer(gamePanel);
    }

    protected Point getOffset() {
    	return offset;
    }
    
    protected void setOffset(Point point) {
        offset = point;
    }
    
    protected double getScale() {
    	return scale;
    }

    protected Point offset(Point p) {
        return new Point(getTileLocation(p).x + offset.x,
        		getTileLocation(p).y + offset.y);
    }
    
    protected Point getTileLocation(Point p) {
        return new Point((int)(0.75f * scale * HEX_W * p.x),
        		(int)(HEX_H * scale * (p.x * 0.5f + p.y)));
    }
    /*
    protected int getTileFromPixelX(int x, int y) {
    	
    }
    */
    
    protected PanelCenterer getPanelCenterer() {
        return panelCenterer;
    }

	public void zoom(double deltaY, double mouseX, double mouseY) {
		int posX = (int) (mouseX - offset.x);
		int posY = (int) (mouseY - offset.y);
		System.out.println(posX + " " + posY);
		if (deltaY > 0) {
			scale += SCALE_AMOUNT;
			//offsetX -= offsetX - (offsetX * scale); //- mouseX/10;
			//offsetY -= offsetY - (offsetY * scale); //- mouseY/10;
		} else {
			scale -= SCALE_AMOUNT;
			//offsetX += offsetX - (offsetX * scale); //- mouseX/10;
			//offsetY += offsetY - (offsetY * scale); //- mouseY/10;
		}
	}
}
