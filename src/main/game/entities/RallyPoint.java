package main.game.entities;
import java.util.UUID;

import game.gameboard.GameBoard;
import game.gameboard.Location;

public class RallyPoint {
	private UUID rallyID;
	private Location location;
	private Army army;
	private GameBoard gameBoard;
	
	public RallyPoint(Location location, GameBoard gameBoard) {
		this.rallyID = UUID.randomUUID();
		this.location = location;
		this.gameBoard = gameBoard;
	}
	
	public void setArmy(Army army) {
		this.army = army;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
}
