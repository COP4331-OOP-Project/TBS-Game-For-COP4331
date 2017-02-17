package view.game;

import game.Assets;
import view.Point;

public class Camera {
    private static final int HEX_W = (int) Assets.getInstance().getImage("TERRAIN_GRASS").getWidth();
    private static final int HEX_H = (int) (HEX_W * 0.86);
    private PanelCenterer panelCenterer;
    private int tileSize;
    private Point offset = new Point(180, -3050);
    private double scale = 1;
	private static final double SCALE_AMOUNT = 0.04;
	private static final double MIN_SCALE = 0.4;
	private static final double MAX_SCALE = 1.5;
    public Camera(GamePanel gamePanel) {
    	tileSize = gamePanel.getTileSize();
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
        return new Point(getPixelLocation(p).x + offset.x,
        		getPixelLocation(p).y + offset.y);
    }
    
    private Point getPixelLocation(Point tile) {
    	return new Point((int)(0.75f * scale * HEX_W * tile.x),
        				 (int)(HEX_H * scale * (tile.x * 0.5f + tile.y)));
    }
    
    public Point getTileLocation(Point pixel) {
    	Point p = new Point(0,0);
		p.x = (int)((pixel.x - offset.x) / (0.75f * scale * HEX_W ));
		p.y = (int)(((pixel.y - offset.y)-((0.5 * HEX_H * scale) * ((pixel.x - offset.x)/(0.75 * scale * HEX_W))))/(HEX_H * scale));
    	return p;
    }
    
    public Point getTileCenter(Point tile) {
    	return new Point ((getPixelLocation(tile).x + tileSize / 2),
    					  (getPixelLocation(tile).y + tileSize / 2));
    }
    
    
    protected PanelCenterer getPanelCenterer() {
        return panelCenterer;
    }

	public void zoom(double deltaY, Point mouseTilePosition) {
		if (deltaY > 0) {
			if (scale < MAX_SCALE) {
				scale += SCALE_AMOUNT;
				panelCenterer.quickCenter(mouseTilePosition);
			}
		} else {
			if (scale > MIN_SCALE) {
				scale -= SCALE_AMOUNT;
				panelCenterer.quickCenter(mouseTilePosition);
			}
		}
	}
}
