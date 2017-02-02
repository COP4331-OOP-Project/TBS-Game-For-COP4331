package game;

public class GameEngine {
	private final int GAME_UPDATES_PER_SECOND = 20;
	private final int TIME_PER_UPDATE = 1000/GAME_UPDATES_PER_SECOND;
	private boolean running = true;
	
	Game game = new Game();
	EventController events;
	Window window;
	
	public static void main(String[] args) {
		new GameEngine();
	}
	
	private GameEngine() {
		Assets.getInstance().loadResources();
		Thread mainThread = new Thread() {
			public void run() {
				events = new EventController(game);
				window = new Window(game, events);
				mainLoop();
			}
		};
		mainThread.start();
	}
	
	/**
	 * A main loop for updating and rendering
	 */
	private void mainLoop() {
		long lastTime = System.currentTimeMillis();
		long accumulatedTime = 0;
		while (running) {
			long newTime = System.currentTimeMillis();
			long diffTime = newTime - lastTime;
			lastTime = newTime;
			accumulatedTime += diffTime;
			while (accumulatedTime >= TIME_PER_UPDATE) {
				game.updateGame();
				window.updateAnimationTime();
				accumulatedTime -= TIME_PER_UPDATE;
			}
			window.renderGame();
		}
	}
}