import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityManager {

	private Handler handler;
	private Player1 player;
	private Player1 player2;
	private ArrayList<Entity> entities;
    private HashMap<String, AudioPlayer> sfx;
	
	//public EntityManager(Handler handler, Player1 player, Player2 player2) {
	public EntityManager(Handler handler, Player1 player, Player1 player2) {
		this.handler = handler;
		this.player = player;
		this.player2 = player2;
		
		entities = new ArrayList<Entity>(); // Array of players
		//Player1 player2 = new Player1(handler, 750, 400);
		//Player1 player = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
		//Player1 player2 = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
		addEntity(player);
		addEntity(player2);
		
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Grass", new AudioPlayer("./media/sounds/side_crash.wav"));
		sfx.put("Car", new AudioPlayer("./media/sounds/crash.wav"));
	}
	
	public void playerCollisions() {
		
			float playerX = entities.get(0).getX();
			float playerY = entities.get(0).getY();
			int playerWidth = entities.get(0).getWidth();
			int playerHeight = entities.get(0).getHeight();
			
			Rectangle playerHitbox = new Rectangle((int)playerX + 5, (int)playerY + 5, playerWidth, playerHeight);
			
			float player2X = entities.get(1).getX();
			float player2Y = entities.get(1).getY();
			int player2Width = entities.get(1).getWidth();
			int player2Height = entities.get(1).getHeight();
			
			// Players collide with each other
			if(playerHitbox.intersects(player2X + 5, player2Y + 5, player2Width, player2Height)) {
				System.out.println("Player collision");
				sfx.get("Car").play();
				//System.exit(0); //end game if players collide
			}

		}
	
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		playerCollisions();
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player1 getPlayer() {
		return player;
	}

	public void setPlayer(Player1 player) {
		this.player = player;
	}

	public Player1 getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player1 player2) {
		this.player2 = player2;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
