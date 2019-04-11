import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player1 extends Players {
	
	public static BufferedImage currentImage;
	
	public Player1(Handler handler, float x, float y) {
		super(handler, x, y, Players.DEFAULT_CREATURE_WIDTH, Players.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 700;
		bounds.y = 350;
		bounds.width = 40;
		bounds.height = 40;
		
	}
	
	@Override
	public void tick() {
		getInput();
		move();
//		collision();
	}
	
	//Update any variables for the object
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			
			yMove = -speed;
			bounds.y = (int)-yMove; 
		if(handler.getKeyManager().down)
			yMove = speed;
			bounds.y = (int)yMove;
		if(handler.getKeyManager().left)
			
			xMove = -speed;
			bounds.x = (int)-speed;
		if(handler.getKeyManager().right)
			xMove = speed;
			bounds.x = (int)xMove;
			
	}

	@Override
	public void render(Graphics g) {
		
		boolean up = false, down = false, left = false, right = false;
		
		if(handler.getKeyManager().up) {
			g.drawImage(Assets.player_move[0], (int)x, (int)y, width, height, null);
			up = true;
		} else if (handler.getKeyManager().down) {
			g.drawImage(Assets.player_move[8], (int)x, (int)y, width, height, null);
			down = true;
		} else if (handler.getKeyManager().right) {
			g.drawImage(Assets.player_move[4], (int)x, (int)y, width, height, null);
			right = true;
		} else if (handler.getKeyManager().left) {
			g.drawImage(Assets.player_move[12], (int)x, (int)y, width, height, null);
			left = true;
		}
//		
//		if(up) {
//			g.drawImage(Assets.player1, (int)x, (int)y, width, height, null);
//		} else if (down) {
//			g.drawImage(Assets.player_move[2], (int)x, (int)y, width, height, null);
//		} else if (right) {
//			g.drawImage(Assets.player_move[1], (int)x, (int)y, width, height, null);
//		} else if (left) {
//			g.drawImage(Assets.player_move[3], (int)x, (int)y, width, height, null);
//		}
		
//		g.drawImage(Assets.player_move[0], (int)x, (int)y, width, height, null);
		
		//g.drawImage(Assets.player1TopLeft, (int)(x + 50), (int)y, width, height, null);
		
		//g.setColor(Color.red);
		//g.fillRect(((int)(x + 5 + bounds.x)), ((int)(y + 5 + bounds.y)), bounds.width, bounds.height); // Hitbox
		//g.fillRect(150, 200, 550, 300); 
		
//		if (collision) {
//			g.setColor(Color.red);
//			g.drawString("Collision", 420, 350);
//			System.out.println("colliders");
//		}


	}

//	public void collision() {
//		Rectangle grass = new Rectangle(150, 200, 550, 300);
//		Rectangle hitbox = new Rectangle(((int)(x + 5 + bounds.x)), ((int)(y + 5 + bounds.y)), bounds.width, bounds.height);
//		Rectangle outside = new Rectangle(50, 100, 750, 500);
//		
//		// Contains checks whether or not this Rectangle entirely is contained the specified Rectangle.
//		// Intersect determines whether or not this Rectangle and the specified Rectangle intersect.
//		if(grass.intersects(hitbox) || !outside.contains(hitbox)){
//			collision = true;
//		} else {
//			collision = false;
//		}
//		
//		//if car hit car end game
//		
//	}

}
