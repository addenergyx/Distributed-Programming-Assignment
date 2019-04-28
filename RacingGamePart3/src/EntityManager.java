import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityManager {

	private Handler handler;
	//private Player player;
	//private Player player2;
	private ArrayList<Entity> entities;
    private HashMap<String, AudioPlayer> sfx;
    private boolean collision;
    private int width = 40, height = 40;

	
	//public EntityManager(Handler handler, Player1 player, Player2 player2) {
	public EntityManager() {
//		this.handler = handler;
//		this.player = player;
//		this.player2 = player2;
		
		entities = new ArrayList<Entity>(); // Array of players
		//Player1 player2 = new Player1(handler, 750, 400);
		//Player1 player = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
		//Player1 player2 = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
//		addEntity(player);
//		addEntity(player2);
		
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Grass", new AudioPlayer("./media/sounds/side_crash.wav"));
		sfx.put("Car", new AudioPlayer("./media/sounds/crash.wav"));
		
	}
	
//	public void playerCollisions() {
//
//		//collision = false;
//		float playerX = entities.get(0).getX();
//		float playerY = entities.get(0).getY();
//		
//		Rectangle playerHitbox = new Rectangle((int)playerX + 5, (int)playerY + 5, width, height );
//		
//		float player2X = entities.get(1).getX();
//		float player2Y = entities.get(1).getY();
//		
//		// Players collide with each other
//		if(playerHitbox.intersects(player2X + 5, player2Y + 5, width, height)) {
//			System.out.println("Player collision");
//			sfx.get("Car").play();
//			collision = true;
//		}
//
//	}
	
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		//playerCollisions(); // End game if players collide
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

//	public Player getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(Player player) {
//		this.player = player;
//	}
//
//	public Player getPlayer2() {
//		return player2;
//	}
//
//	public void setPlayer2(Player player2) {
//		this.player2 = player2;
//	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public boolean isCollision() {
		return collision;
	}

	public void removePlayerMP(String username) {

		int index = 0;
		
		for (Entity e : entities) {
			if(e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
				break;
			}
			index++;
		}
		this.entities.remove(index);
	}
	
	
	private int getPlayerMPIndex(String username) {
		int index = 0;
		
		for (Entity e : entities) {
			if(e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
				break;
			}
			index++;
		}
		return index;
	}
	
	
	public void movePlayer(String username, float x, float y, int currentImageIndex) {
        int index = getPlayerMPIndex(username);
        PlayerMP player = (PlayerMP)this.getEntities().get(index);
        player.x = x;
        player.y = y;
        player.setCurrentImageIndex(currentImageIndex);
	}
	
}
