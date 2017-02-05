package game;

import controls.ModeController;
import controls.Type;
import controls.TypeController;
import controls.TypeInstance.TypeInstanceController;
import game.entities.RallyPoint;
import game.entities.TileOccupant;
import game.gameboard.GameBoard;
import game.gameboard.Location;

import java.util.ArrayList;

public class Game {

	//TODO: change player type to Player instead of Object
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ModeController currentModeController;
	private GameBoard gBoard;
	private Player nextPlayer;
	private int turnNum;



    private Location centerCoordinates;
	private boolean centerCoordinatesUpdated;

	Game() {
		//TODO: initialize game with players
		this.players = new ArrayList<Player>();

		Location initLocation1 = new Location(4,4);
		Location initLocation2 = new Location(4,10);
		Player player0 = new Player(0,initLocation1);
		Player player1 = new Player(1,initLocation2);
		players.add(player0);
		players.add(player1);

		this.nextPlayer = players.get(1);

		turnNum = 0;
		this.currentPlayer = player0;
		gBoard = new GameBoard(players);
		gBoard.getPlayer(0).addRallyPoint(new RallyPoint(new Location(1,1), this.gBoard));
        this.currentModeController = new ModeController(gBoard.getPlayer(0));
        this.centerCoordinatesUpdated = false;
	}

	public void updateGame() { //This is called 20 times per second

	}

	public void startGame() {

	}

	public void endGame() {

	}

	public boolean isFinished() {
		//TODO: determine game is finished conditions
		return false;
	}

	public void createGameBoard() {

	}

	public void endTurn(){
		Player temp = currentPlayer;
		currentPlayer = nextPlayer;
		nextPlayer = temp;
		turnNum++;
	}

	public int getTurnNum() {
		return turnNum;
	}
	
	public GameBoard getGameBoard() {
		return gBoard;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getPlayer(int playerID) {
		return players.get(playerID);
	}

	public Enum getCurrentMode() {
		return this.currentModeController.getMode();
	}

	public Enum getCurrentType() {
		return this.currentModeController.getTypeController().getType();
	}

	protected void cycleModeForward() {
		this.currentModeController.cycleForward();
	}

	protected void cycleModeBackward() {
		this.currentModeController.cycleBackward();
	}

	protected void cycleTypeForward() {
		this.currentModeController.getTypeController().cycleForward();
	}

	protected void cycleTypeBackward() {
		this.currentModeController.getTypeController().cycleBackward();
	}

	protected void cycleCommandForward() {

	}

	protected void cycleCommandBackward() {

	}

	protected void cycleTypeInstanceForward() {
	    TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        Enum currentType = typeController.getType();
        TileOccupant selectedEntity = typeInstanceController.cycleForward(currentType);
        Location newLocation = selectedEntity.getLocation();
	    this.changeCenterCoordinates(newLocation);
	}

	protected void cycleTypeInstanceBackward() {
        this.currentModeController.getTypeController().getTypeInstanceController().cycleBackward(this.currentModeController.getTypeController().getType());
	}

	protected void changeCenterCoordinates(Location loc) {
	    this.centerCoordinates = loc;
	    this.centerCoordinatesUpdated = true;
    }

    public Location getCenterCoordinates() {
        return centerCoordinates;
    }

    public boolean isCenterCoordinatesUpdated() {
        return centerCoordinatesUpdated;
    }

    public void setCenterCoordinatesUpdated(boolean updated) {
	    this.centerCoordinatesUpdated = updated;
    }
}
