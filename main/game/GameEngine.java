package game;

public class GameEngine {
    private final int FPS = 60;
    private final int GAME_UPDATES_PER_SECOND = 20;
    private final int TIME_PER_UPDATE = 1000 / GAME_UPDATES_PER_SECOND;
    Game game = new Game();
    EventController events;
    Window window;
    private boolean running = true;

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
        final long OPTIMAL_TIME = 1000000000 / FPS;
        int accumulatedTime = 0;
        while (running) {
            long newTime = System.currentTimeMillis();
            long updateLength = now - lastTime;
            lastTime = now;

            accumulatedTime += updateLength;

            if (accumulatedTime >= TIME_PER_UPDATE) {
                game.updateGame();
                window.updateAnimationTime();
                accumulatedTime = 0;
            }

            window.renderGame();

            try {
                Thread.sleep((lastTime - System.currentTimeMillis() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}