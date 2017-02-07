package view.game;

import game.Game;
import game.gameboard.Location;

public class PanelCenterer {
	Camera camera;
	GamePanel gamePanel;
	Game game;
	
	private int timeCentering = 0;
	private boolean isCentering = false;
	private int centerStartX = -1;
	private int centerStartY = -1;
	private int centerToX = -1;
	private int centerToY = -1;
	private int selectedX = -1;
	private int selectedY = -1;
	
	private int width = 0;
	private int height = 0;
	
	private static final int TIME_TO_CENTER = 50;
	
	public PanelCenterer(Game game , Camera camera, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.camera = camera;
	}
	
	public void checkWindowCentered(int width, int height) {
		this.width = width;
		this.height = height;
		if (!game.getMovedToNewPlayer()) {
			centerOnNextPlayer();
		}
		if (isCentering) {
			continueCentering();
		}
		checkCenteringCoordinates();
	}
	
	private void centerOnNextPlayer() {
		if (game.getCurrentPlayer().getAllUnit().size() > 0) {
		checkCentering(game.getCurrentPlayer().getAllUnit().
				get(0).getLocation().getX(),game.getCurrentPlayer().
				getAllUnit().get(0).getLocation().getY());
		}
		game.setMovedToNewPlayer(true);
	}
	
	private void checkCentering(int x, int y) {
		if (centeringOffsetX(x) != centerToX || 
				centeringOffsetY(y) != centerToY) {
			centerOnTile(x, y);
		}
		
	}
	
	public void centerOnTile(int x, int y) {
		if (!(camera.getX() == x && camera.getY() == x)) {
			centerStartX = camera.getX();
			centerStartY = camera.getY();
			centerToX = centeringOffsetX(x);
			centerToY = centeringOffsetY(y);
			isCentering = true;
		}
	}
	
	private void continueCentering() {
		if (timeCentering >= TIME_TO_CENTER - 1) {
			camera.setX(centerToX);
			camera.setY(centerToY);
			timeCentering = 0;
			isCentering = false;
		} else {
			camera.setX((int)((percentDoneCentering()*
					(centerToX - centerStartX)) 
					+ centerStartX));
			camera.setY((int)((percentDoneCentering()*
					(centerToY - centerStartY)) 
					+ centerStartY));
			timeCentering++;
		}
	}
	

	protected void checkCenteringCoordinates() {
		if (this.game.isCenterCoordinatesUpdated()) {
			Location loc = this.game.getCenterCoordinates();
			selectedX = loc.getX();
			selectedY = loc.getY();
			this.checkCentering(selectedX, selectedY);
			this.game.setCenterCoordinatesUpdated(false);
		}
	}
	
	//Returns the X offset based on the X Location of a tile for centering
	private int centeringOffsetX(int x) {
		return (width/2) - camera.getTileLocation(x) - gamePanel.getTileSize()/2;
	}
	
	//Returns Y Offset Based on the Y Location of a Tile for centering
	private int centeringOffsetY(int y) {
		return (height/2) - camera.getTileLocation(y) - gamePanel.getTileSize()/2;
	}
	
	
	//How close to the destination the view is
	private double percentDoneCentering() {
		return (Math.log(((double)timeCentering/
				(double)TIME_TO_CENTER*(19.1)) + 1)) / 3;
	}
	
	public int getSelectedX() {
		return selectedX;
	}
	
	public int getSelectedY() {
		return selectedY;
	}
}
