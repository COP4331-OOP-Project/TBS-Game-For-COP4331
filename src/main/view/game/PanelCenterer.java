package view.game;

public class PanelCenterer {
	GamePanel gamePanel;
	
	private int timeCentering = 0;
	private boolean isCentering = false;
	private int centerStartX = -1;
	private int centerStartY = -1;
	private int centerToX = -1;
	private int centerToY = -1;
	
	private int width = 0;
	private int height = 0;
	
	private static final int TIME_TO_CENTER = 50;
	
	public PanelCenterer(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void recenter(int width, int height) {
		this.width = width;
		this.height = height;
		if (isCentering)
			continueCentering();
	}
	
	public void centerOnTile(int x, int y) {
		if (centeringOffsetX(x, y) != centerToX || 
				centeringOffsetY(x, y) != centerToY) {
			centerStartX = gamePanel.getCamera().getX();
			centerStartY = gamePanel.getCamera().getY();
			centerToX = centeringOffsetX(x, y);
			centerToY = centeringOffsetY(x, y);
			isCentering = true;
		}
	}
	
	//Returns the X offset based on the X Location of a tile for centering
	private int centeringOffsetX(int x, int y) {
		return (width/2) - gamePanel.getCamera().getTileLocationX(x, y) - gamePanel.getTileSize()/2;
	}
	
	//Returns Y Offset Based on the Y Location of a Tile for centering
	private int centeringOffsetY(int x, int y) {
		return (height/2) - gamePanel.getCamera().getTileLocationY(x, y) - gamePanel.getTileSize()/2;
	}
	
	private void continueCentering() {
		if (timeCentering >= TIME_TO_CENTER - 1) {
			gamePanel.getCamera().setX(centerToX);
			gamePanel.getCamera().setY(centerToY);
			timeCentering = 0;
			isCentering = false;
		} else {
			gamePanel.getCamera().setX((int)((percentDoneCentering()*(centerToX - centerStartX)) 
					+ centerStartX));
			gamePanel.getCamera().setY((int)((percentDoneCentering()*(centerToY - centerStartY)) 
					+ centerStartY));
			timeCentering++;
		}
	}
	
	//How close to the destination the view is
	private double percentDoneCentering() {
		return (Math.log(((double)timeCentering/(double)TIME_TO_CENTER*(19.1)) + 1)) / 3;
	}

}
