import java.awt.Graphics;

public class GameState extends State {

	private Player1 player1;
	private Player2 player2;
	private EntityManager entityManager;
	
	public GameState(Handler handler) {
		super(handler);
		
		entityManager = new EntityManager(handler, new Player1(handler, 700, 350), new Player2(handler, 750, 350) );
		
		//player1 = new Player1(handler, 700,350);
		//player2 = new Player2(handler, 750, 350);
	}
	
	@Override
	public void tick() {
		//player1.tick();
		//player2.tick();
		
		entityManager.tick();
	}

	@Override
	public void render(Graphics g) {
		//player1.render(g);
		//player2.render(g);
		
		entityManager.render(g);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
