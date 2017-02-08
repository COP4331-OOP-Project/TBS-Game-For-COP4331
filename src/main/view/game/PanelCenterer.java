package view.game;

import game.Game;
import game.gameboard.Location;

public class PanelCenterer {
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
	
	public PanelCenterer(Game game , GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
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
		System.out.println(centeringOffsetX(1));
		System.out.println(gamePanel.getCamera().getTileLocation(selectedY));
		if (gamePanel.getCamera().getX() != gamePanel.getCamera().getTileLocation(selectedX) 
				&& gamePanel.getCamera().getY() != gamePanel.getCamera().getTileLocation(selectedY)) {
			checkCentering(selectedX, selectedY);
		}
	}
	
	private void checkCenteringCoordinates() {
		if (this.game.isCenterCoordinatesUpdated()) {
			Location loc = this.game.getCenterCoordinates();
			selectedX = loc.getX();
			selectedY = loc.getY();
			this.checkCentering(selectedX, selectedY);
			this.game.setCenterCoordinatesUpdated(false);
		}
	}
	
	public void centerOnTile(int x, int y) {
		if (gamePanel.getCamera().getX() == x &&
			gamePanel.getCamera().getY() == y)
				return;
		centerStartX = gamePanel.getCamera().getX();
		centerStartY = gamePanel.getCamera().getY();
		centerToX = centeringOffsetX(x);
		centerToY = centeringOffsetY(y);
		isCentering = true;
	}
	
	//Returns the X offset based on the X Location of a tile for centering
	private int centeringOffsetX(int x) {
		return (width/2) - gamePanel.getCamera().getTileLocation(x) - gamePanel.getTileSize()/2;
	}
	
	//Returns Y Offset Based on the Y Location of a Tile for centering
	private int centeringOffsetY(int y) {
		return (height/2) - gamePanel.getCamera().getTileLocation(y) - gamePanel.getTileSize()/2;
	}
	
	private void continueCentering() {
		if (timeCentering >= TIME_TO_CENTER - 1) {
			gamePanel.getCamera().setX(centerToX);
			gamePanel.getCamera().setX(centerToY);
			timeCentering = 0;
			isCentering = false;
		} else {
			gamePanel.getCamera().setX((int)((percentDoneCentering()*(centerToX - centerStartX)) 
					+ centerStartX));
			gamePanel.getCamera().setX((int)((percentDoneCentering()*(centerToY - centerStartY)) 
					+ centerStartY));
			timeCentering++;
		}
	}
	
	private void checkCentering(int x, int y) {
		if (centeringOffsetX(x) != centerToX || 
				centeringOffsetY(y) != centerToY) {
			centerOnTile(x, y);
		}	
	}
	
	private void centerOnNextPlayer() {
		if (game.getCurrentPlayer().getAllUnit().size() > 0) {
		checkCentering(game.getCurrentPlayer().getAllUnit().
				get(0).getLocation().getX(),game.getCurrentPlayer().getAllUnit().
				get(0).getLocation().getY());
		}
		game.setMovedToNewPlayer(true);
	}
	
	//How close to the destination the view is
	private double percentDoneCentering() {
		return (Math.log(((double)timeCentering/(double)TIME_TO_CENTER*(19.1)) + 1)) / 3;
	}
	
	public int getSelectedX() {
		return selectedX;
	}
	
	public int getSelectedY() {
		return selectedY;
	}
}
