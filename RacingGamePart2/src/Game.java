import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
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
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		track = new Track(title, width, height);
		track.getFrame().addKeyListener(keyManager);
		Assets.init(); //loads all images
		
		handler = new Handler(this);
		
		//Initialises State of game
		gameState = new GameState(handler); //Because already in game class just pass 'this'
		menuState = new MenuState(handler);
		State.setState(gameState);

	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
		
	private void render() {
		bs = track.getCanvas().getBufferStrategy(); // Using buffers to draw to screen
		if(bs == null) {
			track.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g.create();
		
		// Clear to hide flashing track update
		g.clearRect(0, 0, width, height);
		
		g.fillRect(0, 0, width, height); // Track
		
		Color c1 = Color.green;
		g.setColor( c1 );
		g.fillRect( 150, 200, 550, 300 ); //grass
		
		Color c2 = Color.black;
		g.setColor( c2 );
		g.drawRect(50, 100, 750, 500);  // outer edge
		g.drawRect(150, 200, 550, 300); // inner edge
		
		g2d.setColor(Color.yellow);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2d.setStroke(dashed);
	    g2d.draw(new RoundRectangle2D.Double(100, 150, 650, 400, 10, 10));
		
		Color c4 = Color.white;
		g.setColor( c4 );
		g.drawLine( 700, 350, 800, 350 ); // start line
		
		//Outer grass
		Area outter = new Area(new Rectangle(0, 0, 850, 650 ));
		Rectangle inner = new Rectangle(50, 100, 750, 500);
		outter.subtract(new Area(inner));
		g2d.setColor(Color.green);
		g2d.fill(outter);
		
		//Calling order in java really matters!!! Must call player kart last
		if(State.getState() != null)
			State.getState().render(g);
		
		//g.drawImage(testImage, 20,20, null);
		//g.drawImage(sheet.crop(0, 0, 50, 50),5,5,null);
		//g.drawImage(Assets.player1,10,10, null);
		
		//player1.render(g); //player1 kart
		
		bs.show();
		g.dispose();
		g2d.dispose();
		
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
