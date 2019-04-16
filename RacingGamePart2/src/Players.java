import java.awt.Rectangle;
import java.util.HashMap;

public abstract class Players extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 0f; //stationary
	public static final int DEFAULT_PLAYER_WIDTH = 50;
	public static final int DEFAULT_PLAYER_HEIGHT = 50;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	boolean collision;
    private HashMap<String, AudioPlayer> sfx;
    private AudioPlayer sfx2;

	public Players(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
		// Car sound effects
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Grass", new AudioPlayer("crash"));
		sfx.put("Car", new AudioPlayer("side_crash"));

	}

	public void move(){

		if (collision) {
			System.out.println("collision");
			speed = DEFAULT_SPEED;
		}
		
		sfx2 = new AudioPlayer("crash");
		sfx2.play();
		
		sfx.get("Car").play();
		
		Rectangle grass = new Rectangle(150, 200, 550, 300);
		Rectangle hitbox = new Rectangle(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); //player boundary
		Rectangle outside = new Rectangle(50, 100, 750, 500);
		Rectangle finishline = new Rectangle( 700, 400, 800, 1 );

		if(grass.intersects(hitbox) || !outside.contains(hitbox)){
			sfx.get("Grass").play();
		}
		
		if (finishline.intersects(hitbox)) {
			System.out.println("Finished");
		}
		
		//System.out.println(xMove);
		if (xMove > 0) {
	
			if (grass.intersects(hitbox)) {
				x = x - 20;
				speed = 0;
			} else if (!outside.contains(hitbox)) {
				x = x - 20;
				speed = 0;
			}else {
				x += xMove;
			}
		
		} else if (xMove < 0) {
			
			if (grass.intersects(hitbox)) {
				x = x + 20;
				speed = 0;
			} else if (!outside.contains(hitbox)) {
				x = x + 20;
				speed = 0;
			} else {
				x += xMove;
			}
		
		}
		
		if (yMove > 0) {
			
			if(grass.intersects(hitbox)) {
				y = y - 20;
				speed = 0;
			}else if(!outside.contains(hitbox)) {
				y = y - 20;
				speed = 0;
			}else {
				y += yMove;
			}
			
		} else if (yMove < 0) {
			
			if(grass.intersects(hitbox)) {
				y = y + 20;
				speed = 0;
			}else if(!outside.contains(hitbox)) {
				y = y + 20;
				speed = 0;
			} else {
				y += yMove;
			}
			
		}
		
//		// Contains checks whether or not this Rectangle entirely is contained the specified Rectangle.
//		// Intersect determines whether or not this Rectangle and the specified Rectangle intersect.
//		if(grass.intersects(hitbox) || !outside.contains(hitbox)){
//			
//			
//			collision = true;
//
//			speed = 0;
//			
//			if (xMove > 0 ) { //Moving to the right
//			//	speed = 0;
//				int tx = (int)(x + xMove + bounds.x + bounds.width);
//				
//				if (tx <= y + bounds.y && tx <= y + bounds.y + bounds.height){
//					x += xMove;
//				} else {
//					x = tx - bounds.x - bounds.width - 50; 
//				}
//				
//				//x = ( x + xMove + bounds.x + bounds.width);//bounce back towards middle
//				
//			} else if (xMove < 0) { //Moving to the left
//				int tx = (int)(x + xMove + bounds.x);
//
//				if (tx <= y + bounds.y && tx >= y + bounds.y + bounds.height){
//					x += xMove;
//				} else {
//					x = (tx - bounds.x + 50 );
//					//x = (x + bounds.height);
//				}
//				
//			}
//			
//			
//			if (yMove < 0 ) { //Moving up
//				int ty = (int)(y + yMove + bounds.y);
//				
//				if (ty >= (int) (x + bounds.x) && ty >= (int) (x + bounds.x + bounds.width)) {
//					y += yMove;
//				} else {
//					y = ty - bounds.y + 50;
//				}
//				
//				//y = ( y - bounds.height);
//			} else if (yMove > 0){ //Moving down
//				int ty = (int)(y + yMove + bounds.y + bounds.height);
//				
//				if (ty <= (int) (x + bounds.x) && ty <= (int) (x + bounds.x + bounds.width)) {
//					y += yMove;
//					if (grass.intersects(hitbox)) {
//						y = y - 50;
//					}
//					if (!outside.contains(hitbox)) {
//						y = y - 50;
//					}
//					System.out.println("im stuck");
//				} else {
//					//y = ty - bounds.y - bounds.height - 100;
//					y = y - 50;
//				}
//				
//				
//				//y = ( y + bounds.height);
//			}

			
//		} else {
//			collision = false;
//			x += xMove;
//			y += yMove;
//		
//			//moveX();
//			//moveY();
//		}
		
		
	}
	
//	public void moveX() {
//		x += xMove;
//		if (collision) {
//			System.out.println("i got hit");
//		}
//
//		
//	}

	public void moveY() {
		y += yMove;
	}
	
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
	
	public void direction(int currentImageIndex) {
		
		if (currentImageIndex == 0 ) {
			yMove = yMove - 2 * speed;
			//bounds.y = (int)yMove;
		} else if (currentImageIndex == 1 ) {
			xMove = xMove + 1 * speed;
			yMove = yMove - 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		} else if (currentImageIndex == 2 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove - 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		} else if (currentImageIndex == 3 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove - 1 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		} else if (currentImageIndex == 4 ) {
			xMove = xMove + 2 * speed;
			//bounds.x = (int)xMove;
		}else if (currentImageIndex == 5 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove + 1 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 6 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove + 1 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 7 ) {
			xMove = xMove + 1 * speed;
			yMove = yMove + 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 8 ) {
			yMove = yMove + 2 * speed;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 9 ) {
			xMove = xMove - 1 * speed;
			yMove = yMove + 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 10 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove + 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 11 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove + 1 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 12 ) {
			xMove = xMove - 2 * speed;
			//bounds.x = (int)xMove;
		}else if (currentImageIndex == 13 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove - 1 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}else if (currentImageIndex == 14 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove - 2 * speed;
			//bounds.y = (int)yMove;
			//bounds.x = (int)xMove;
		}else if (currentImageIndex == 15 ) {
			xMove = xMove - 1 * speed;
			yMove = yMove - 2 * speed;
			//bounds.x = (int)xMove;
			//bounds.y = (int)yMove;
		}
	}
}
