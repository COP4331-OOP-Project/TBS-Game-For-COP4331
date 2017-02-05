package game;

import controls.ModeController;
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



	Game() {
		//TODO: initialize game with players
		this.players = new ArrayList<Player>();

		Location initLocation1 = new Location(4,4);
		Location initLocation2 = new Location(4,10);
		Player player0 = new Player(0,initLocation1);
		Player player1 = new Player(1,initLocation2);
		players.add(player0);
		players.add(player1);
		this.currentPlayer = players.get(0);
		this.nextPlayer = players.get(1);
		this.currentModeController = new ModeController(this.currentPlayer);
		gBoard = new GameBoard(players);

		turnNum = 0;
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

	}

	protected void cycleTypeInstanceBackward() {

	}
}
