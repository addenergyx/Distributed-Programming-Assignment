import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityManager {

	private Handler handler;
	private Player player;
	private Player player2;
	private ArrayList<Entity> entities;
    private boolean collision;
    private int width = 40, height = 40;

	
	//public EntityManager(Handler handler, Player1 player, Player2 player2) {
	public EntityManager(Handler handler, Player player, Player player2) {
		this.handler = handler;
		this.player = player;
		this.player2 = player2;
		
		entities = new ArrayList<Entity>(); // Array of players
		addEntity(player);
		addEntity(player2);
		
	}
	
	public void playerCollisions() {

		//collision = false;
		float playerX = entities.get(0).getX();
		float playerY = entities.get(0).getY();
		
		Rectangle playerHitbox = new Rectangle((int)playerX + 5, (int)playerY + 5, width, height );
		
		float player2X = entities.get(1).getX();
		float player2Y = entities.get(1).getY();
		
		// Players collide with each other
		if(playerHitbox.intersects(player2X + 5, player2Y + 5, width, height)) {
			//System.out.println("Player collision");
			Assets.sfx.get("Car").play();
			collision = true;
		}

	}
	
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		playerCollisions(); // End game if players collide
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
		
//		float player2X = entities.get(1).getX();
//		float player2Y = entities.get(1).getY();
//		
//		g.setColor(Color.red);
//		g.fillRect((int)player2X + 5, (int)player2Y + 5, 40, 40); // Hitbox
//		
//		float playerX = entities.get(0).getX();
//		float playerY = entities.get(0).getY();
//		
//		g.setColor(Color.red);
//		g.fillRect((int)playerX + 5, (int)playerY + 5, 40, 40 ); // Hitbox
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public boolean isCollision() {
		return collision;
	}
	
	
}
