import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JOptionPane;

public class GameState extends State {

	private EntityManager entityManager;
	private int collisions = 0;
	
	public GameState(Handler handler) {
		super(handler);
		
		entityManager = new EntityManager(handler, new Player(handler, 700, 350, Assets.player1_move, "wasd"  ), new Player(handler, 750, 350, Assets.player2_move, "arrows") );
	}
	
	@Override
	public void tick() {
		
		entityManager.tick();
		
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		
		Color c1 = Color.green;
		g.setColor( c1 );
		g.fillRect( 150, 200, 550, 300 ); //Grass
		
		Color c2 = Color.black;
		g.setColor( c2 );
		g.drawRect(50, 100, 750, 500);  // Outer edge
		g.drawRect(150, 200, 550, 300); // Inner edge
		
		g2d.setColor(Color.yellow);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2d.setStroke(dashed); // Road markings
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
		
		for (Entity e : entityManager.getEntities()) {
			g.drawImage(Assets.fire, (int)e.x, (int)e.y,null);
		}	
		
		
		if (collisions == 1) {
			JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
			System.exit(0);
		}
		
		collisions++;
	}
	
}
