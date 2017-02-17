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
            centerStart.x = gamePanel.getCamera().getOffset().x;
            centerStart.y = gamePanel.getCamera().getOffset().y;
            centerTo.x = centeringOffset(p).x;
            centerTo.y = centeringOffset(p).y;
            isCentering = true;
        }
    }
    
    private Point centeringOffset(Point p) {
        return new Point((width / 2) - gamePanel.getCamera().getTileLocation(p).x - gamePanel.getTileSize() / 2,
        				((height / 2) - gamePanel.getCamera().getTileLocation(p).y - gamePanel.getTileSize() / 2));
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
