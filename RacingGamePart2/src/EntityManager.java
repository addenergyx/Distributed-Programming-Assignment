import java.awt.Graphics;
import java.util.ArrayList;

public class EntityManager {

	private Handler handler;
	private Player1 player;
	private Player1 player2;
	private ArrayList<Entity> entities;
	
	//public EntityManager(Handler handler, Player1 player, Player2 player2) {
	public EntityManager(Handler handler, Player1 player, Player1 player2) {
		this.handler = handler;
		this.player = player;
		this.player2 = player2;
		
		// Array of players
		entities = new ArrayList<Entity>();
		//Player1 player2 = new Player1(handler, 750, 400);
		//Player1 player = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
		//Player1 player2 = new Player1(handler, 750, 400, Assets.player1_move, "wasd");
		addEntity(player);
		addEntity(player2);
		//addEntity(player2);
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
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

//	public Player2 getPlayer2() {
//		return player2;
//	}
//
//	public void setPlayer2(Player2 player2) {
//		this.player2 = player2;
//	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
