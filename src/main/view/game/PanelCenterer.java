package view.game;

import view.Point;

public class PanelCenterer {
    private static final int TIME_TO_CENTER = 50;
    GamePanel gamePanel;
    private int timeCentering = 0;
    private boolean isCentering = false;
    private Point centerStart = new Point(-1,-1);
    private Point centerTo = new Point(-1,-1);
    private int width = 0;
    private int height = 0;

    public PanelCenterer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void recenter(int width, int height) {
        this.width = width;
        this.height = height;
        if (isCentering)
            continueCentering();
    }

    public void centerOnTile(Point p) {
        if (centeringOffset(p).x != centerTo.x ||
                centeringOffset(p).y != centerTo.y) {
            centerStart = gamePanel.getCamera().getOffset();
            centerTo = centeringOffset(p);
            isCentering = true;
        }
    }
    
	public void quickCenter(Point position) {
		gamePanel.getCamera().setOffset(centeringOffset(position));
	}
    
    private Point centeringOffset(Point p) {
        return new Point((width / 2) - gamePanel.getCamera().getTileCenter(p).x,
        				((height / 2) - gamePanel.getCamera().getTileCenter(p).y));
    }


    private void continueCentering() {
        if (timeCentering >= TIME_TO_CENTER - 1) {
            gamePanel.getCamera().setOffset(new Point(centerTo.x, centerTo.y));
            timeCentering = 0;
            isCentering = false;
        } else {
            gamePanel.getCamera().setOffset(new Point((int)((percentDoneCentering() * (centerTo.x - centerStart.x)) + centerStart.x), 
            									       (int)(percentDoneCentering() * (centerTo.y - centerStart.y) + centerStart.y)));
            timeCentering++;
        }
    }

    //How close to the destination the view is
    private double percentDoneCentering() {
        return (Math.log(((double) timeCentering / (double) TIME_TO_CENTER * (19.1)) + 1)) / 3;
    }
    
   	public void stopCentering() {
    	isCentering = false;
    	timeCentering = 0;
    }

}
