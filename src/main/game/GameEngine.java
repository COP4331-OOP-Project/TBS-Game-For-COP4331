package game;

import view.Window;

public class GameEngine {
	private final int FPS = 60;
	private final int GAME_UPDATES_PER_SECOND = 20;
	private final int TIME_PER_UPDATE = 1000/GAME_UPDATES_PER_SECOND;
	private boolean running = true;
	
	Game game = new Game();
	EventController events;
	Window window;
	
	public GameEngine() {
		Thread mainThread = new Thread() {
			public void run() {
				window = new Window();

				System.out.println("Rendering");
				window.show();

				window.startGame(game);
				events = new EventController(game);
				//window.addKeyListener(events);
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
		   int accumulatedTime = 0;
		   long fpsTime = 1000000000 / FPS;
		   while (running)
		   {
		      long newTime = System.currentTimeMillis();
		      long diffTime = newTime - lastTime;
		      lastTime = newTime;
		      accumulatedTime += diffTime;
		      if (accumulatedTime >= TIME_PER_UPDATE)
		      {
		         game.updateGame();
				 window.updateAnimationTime();
				 accumulatedTime = 0;
		      }
		      window.renderGame();
		      
		      try {
				Thread.sleep( (lastTime - System.currentTimeMillis() + fpsTime)/1000000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		   }
	}
}