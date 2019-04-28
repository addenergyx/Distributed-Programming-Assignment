import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game implements Runnable{
	
	public Track track; // Public because need frame for window listener
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
	public EntityManager entityManager;
	public WindowHandler windowHandler;
	
	//Handler
	public Handler handler;
	
	public Player player;
	
	public GameClient socketClient;
	public GameServer socketServer;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	public void init() {
		
		
		track = new Track(title, width, height);
		track.getFrame().addKeyListener(keyManager);
		//track.getFrame().addWindowListener(windowHandler);
		Assets.init(); // Loads all images
//		handler = new Handler(this);
		windowHandler = new WindowHandler(this);

		entityManager = new EntityManager();
		
		player = new PlayerMP(this, 750, 350, JOptionPane.showInputDialog(this, "Username"), Assets.player1_move, "arrows" ,null, -1); // Original player

		entityManager.addEntity(player);
		
		Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.x, player.y);
		
		if (socketServer != null) {
			socketServer.addConnection((PlayerMP)player, loginPacket);
		}
		
		//socketClient.sendData("ping".getBytes());
		loginPacket.writeData(socketClient);
		
		// Initialises State of game
//		gameState = new GameState(handler); 
//		menuState = new MenuState(handler);
//		State.setState(gameState);
		
	}
	
	private void tick() {
		keyManager.tick();
		entityManager.tick();
		
//		if(State.getState() != null)
//			State.getState().tick();
	}
		
	private void render() {
		bs = track.getCanvas().getBufferStrategy(); // Using buffers to draw to screen
		if(bs == null) {
			track.getCanvas().createBufferStrategy(3); // Triple buffer is standard design
			return;
		}
		
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g.create();
		
		// Clear to hide flashing track update
		g.clearRect(0, 0, width, height);
		
		g.fillRect(0, 0, width, height); // Track
		
		Color c1 = Color.green;
		g.setColor( c1 );
		g.fillRect( 150, 200, 550, 300 ); //Grass
		
		Color c2 = Color.black;
		g.setColor( c2 );
		g.drawRect(50, 100, 750, 500);  // Outer edge
		g.drawRect(150, 200, 550, 300); // Inner edge
		
		g2d.setColor(Color.yellow);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2d.setStroke(dashed);
	    g2d.draw(new RoundRectangle2D.Double(100, 150, 650, 400, 10, 10));
		
		Color c4 = Color.white;
		g.setColor( c4 );
		g.drawLine( 700, 350, 800, 350 ); // Start line
		
		g.setColor(Color.red);
		g.fillRect(50, 350, 101, 10 ); // Checkpoint
		
		//Outer grass
		Area outer = new Area(new Rectangle(0, 0, 850, 650 ));
		Rectangle inner = new Rectangle(50, 100, 750, 500);
		outer.subtract(new Area(inner));
		g2d.setColor(Color.green);
		g2d.fill(outer);
		
//		if(State.getState() != null)
//			State.getState().render(g);
		
		//player.render(g);
		entityManager.render(g);
		
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

	public synchronized void start() {
//		if(running) // Prevents another thread starting
//			return;
		running = true;
		
		int reply = JOptionPane.showConfirmDialog(null, "Running server?", "Server", JOptionPane.YES_NO_OPTION);
		//if(JOptionPane.showInputDialog(game, "Running server?").trim() == "yes") {
		if (reply == JOptionPane.YES_OPTION) {
			socketServer = new GameServer(this);
			socketServer.start();
			System.out.println("Server");
		} 
		
		new Thread(this).start();
		
        socketClient = new GameClient(this, "127.0.0.1");
        socketClient.start();
       
	}
	
	public synchronized void stop() {
//		if(!running) // Prevents trying to stop thread that is already stopped
//			return;
		running = false;
		
		try {
			thread.join(); // Stops thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
