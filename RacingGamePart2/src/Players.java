import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Players extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50;
	public static final int DEFAULT_CREATURE_HEIGHT = 50;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove, speedUp, speedDown;
	boolean collision;


	public Players(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move(){

		Rectangle grass = new Rectangle(150, 200, 550, 300);
		Rectangle hitbox = new Rectangle(((int)(x + 5 + bounds.x)), ((int)(y + 5 + bounds.y)), bounds.width, bounds.height); //player boundary
		Rectangle outside = new Rectangle(50, 100, 750, 500);
		
		// Contains checks whether or not this Rectangle entirely is contained the specified Rectangle.
		// Intersect determines whether or not this Rectangle and the specified Rectangle intersect.
		if(grass.intersects(hitbox) || !outside.contains(hitbox)){
			collision = true;
			System.out.println("collision");
			
			if (xMove > 0 ) { //right boundary
				x = ( x - 2);//bounce back towards middle
			} else if (xMove < 0) { //left boundary
				x = (x + 2);
			}
			
			if (yMove > 0 ) { //up boundary
				y = ( y - 2);
			} else if (yMove < 0){ //down boundary
				y = ( y + 2);
			}

		} else {
			collision = false;
			x += xMove;
			y += yMove;
		}
		
		//moveX();
		//moveY();
	}
	
//	public void moveX() {
//		x += xMove;
//		if (collision) {
//			System.out.println("i got hit");
//		}
//
//		
//	}
//	
//	public void moveY() {
//		y += yMove;
//	}
	
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
