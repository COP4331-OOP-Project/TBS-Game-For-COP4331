package game;

import controls.ModeController;
import game.gameboard.GameBoard;

import java.util.ArrayList;

public class Game {

	//TODO: change player type to Player instead of Object
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ModeController currentModeController;
	private GameBoard gBoard;

	Game() {
		//TODO: initialize game with players
		this.currentModeController = new ModeController();
		players = new ArrayList<Player>();
		Player player0 = new Player(0);
		Player player1 = new Player(1);
		players.add(player0);
		players.add(player1);
		gBoard = new GameBoard(players);
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
	
	public GameBoard getGameBoard() {
		return gBoard;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
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
		this.currentModeController.getTypeController().cycleForward(this.currentModeController.getMode());
	}

	protected void cycleTypeBackward() {
		this.currentModeController.getTypeController().cycleBackward(this.currentModeController.getMode());
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
