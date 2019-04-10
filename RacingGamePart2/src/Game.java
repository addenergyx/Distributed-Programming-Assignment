import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

	private Track track;
	public int width, height;
	public String title;
	
	private boolean running = false; // When players collide running should become false
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Player player1;
	private Game game;
	
	//temp
	//private BufferedImage testImage;
	private SpriteSheet sheet;
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		track = new Track(title, width, height);
		track.getFrame().addKeyListener(keyManager);
		game = new Game(title, width, height);
		Assets.init(); //loads all images
		
		player1 = new Player(game, 100, 100);
		//testImage = KartLoader.loadImage("/player1gokart/spritesheet.png");
		//sheet = new SpriteSheet(testImage);
	}
	
		private void tick() {
		keyManager.tick();
		player1.tick();
	}
		
	private void render() {
		bs = track.getCanvas().getBufferStrategy(); // Using buffers to draw to screen
		if(bs == null) {
			track.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
//		// Clear to hide flashing track update
//		g.clearRect(0, 0, width, height);
//		
//		g.fillRect(0, 0, width, height); // Track
//		
//		Color c1 = Color.green;
//		g.setColor( c1 );
//		g.fillRect( 150, 200, 550, 300 ); //grass
//		
//		Color c2 = Color.black;
//		g.setColor( c2 );
//		g.drawRect(50, 100, 750, 500);  // outer edge
//		g.drawRect(150, 200, 550, 300); // inner edge
//		
//		Color c3 = Color.yellow;
//		g.setColor( c3 );
//		g.drawRect( 100, 150, 650, 400 ); // mid-lane marker
//		
//		Color c4 = Color.white;
//		g.setColor( c4 );
//		g.drawLine( 700, 350, 800, 350 ); // start line
		
		//g.drawImage(testImage, 20,20, null);
		//g.drawImage(sheet.crop(0, 0, 50, 50),5,5,null);
		//g.drawImage(Assets.player1,10,10, null);
		
		player1.render(g); //player1 kart
		
		bs.show();
		g.dispose();
		
	}


	
	public void run() {
		
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

	public synchronized void start() {
		if(running) // Prevents another thread starting
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
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
