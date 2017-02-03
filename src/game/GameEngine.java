package game;

public class GameEngine {
	private final int GAME_UPDATES_PER_SECOND = 20;
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
			  int loops;
			  while (running) {
				      loops = 0;
				      while (System.currentTimeMillis() > lastTime && loops < 10) {
				    	  game.updateGame();
				    	  lastTime += GAME_UPDATES_PER_SECOND;
				    	  loops++;
				      }
				      window.renderGame();
			   }
		}
}