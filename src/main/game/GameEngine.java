package game;

public class GameEngine {
	private static final long RENDER_WAIT = 50;
	private final int GAME_UPDATES_PER_SECOND = 20;
	private final int TIME_PER_UPDATE = 1000/GAME_UPDATES_PER_SECOND;
	private boolean running = true;
	
	Game game = new Game();
	EventController events;
	Window window;
	
	public GameEngine() {
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
		long fpsTime = 0;
		while (running) {
			long newTime = System.currentTimeMillis();
			long diffTime = newTime - lastTime;
			lastTime = newTime;
			fpsTime += diffTime;
			accumulatedTime += diffTime;
			while (accumulatedTime >= TIME_PER_UPDATE) {
				game.updateGame();
				window.updateAnimationTime();
				accumulatedTime -= TIME_PER_UPDATE;
			}
			while (fpsTime >= RENDER_WAIT ){
				window.renderGame();
				fpsTime -= RENDER_WAIT;
			}
				
		}
	}
}