import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game implements Runnable{
	
	private Track track;
	public int width, height;
	public String title;
	
	private boolean running = false; // When players collide running should become false
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
		
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		track = new Track(title, width, height);
		track.getFrame().addKeyListener(keyManager);
		
		// Need both as sometimes frame isn't in focus
		track.getFrame().addMouseListener(mouseManager);
		track.getCanvas().addMouseListener(mouseManager);
		
		Assets.init(); // Loads all images
		
		handler = new Handler(this);
		
		// Initialises State of game
		gameState = new GameState(handler); 
		menuState = new MenuState(handler);
		
		State.setState(menuState);

	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
		
	private void render() {
		bs = track.getCanvas().getBufferStrategy(); // Using buffers to draw to screen
		if(bs == null) {
			track.getCanvas().createBufferStrategy(3); // Triple buffer is standard design
			return;
		}
		
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g.create();
		
		g.clearRect(0, 0, width, height); // Clear to hide flashing track update
		
		g.fillRect(0, 0, handler.getGame().width, handler.getGame().height); // Track for game, background for menu
		
		if(State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
		g2d.dispose();
		
	}


	
	public State getGameState() {
		return gameState;
	}

	public void run() {
		running = true;
		
		init();
		
		// Doing this to ensure kart speed is the same on all types of computers
		int fps = 60; //frames per second
		double timePerTick = 1000000000 / fps; // Measuring time in nanoseconds, Max tick
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); // System clock

		// Game loop
		while(running) { 
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}

		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

//	public synchronized void start() {
//		if(running) // Prevents another thread starting
//			return;
//		running = true;
//		thread = new Thread(this);
//		thread.start();
//	}
	
	public synchronized void stop() {
		if(!running) // Prevents trying to stop thread that is already stopped
			return;
		running = false;
		
		try {
			thread.join(); // Stops thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
