package game;

import controls.ModeController;
import java.util.ArrayList;

public class Game {

	//TODO: change player type to Player instead of Object
	private Object currentPlayer;
	private ArrayList<Object> players;
	private ModeController currentModeController;

	Game() {
		//TODO: initialize game with players
		this.currentModeController = new ModeController();
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

	public void addPlayer(Object p) {
		this.players.add(p);
	}

	public Object getCurrentPlayer() {
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
