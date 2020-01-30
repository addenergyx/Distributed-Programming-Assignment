import java.awt.Graphics;

import javax.swing.JOptionPane;

public class GameState extends State {

	//private Player1 player1;
	//private Player2 player2;
	private EntityManager entityManager;
	
	public GameState(Handler handler) {
		super(handler);
		
		//entityManager = new EntityManager(handler, new Player(handler, 700, 350, Assets.player1_move, "wasd"  ), new Player(handler, 750, 350, Assets.player2_move, "arrows") );
		//entityManager = new EntityManager(handler, new Player1(handler, 700, 350, Assets.player2_move, "wasd"));
				
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
		
		if (entityManager.isCollision()) {
			gameOver(g);
		}
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void gameOver(Graphics g) {
//		g.setColor(Color.white);
//		g.setFont(new Font("arial", Font.BOLD, 50));
//		g.drawString("Game Over", 300, 360);
		JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
	
}
