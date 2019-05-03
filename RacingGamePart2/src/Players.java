import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Players extends Entity {

	public static final float DEFAULT_SPEED = 0f; //stationary
	public static final int DEFAULT_PLAYER_WIDTH = 50;
	public static final int DEFAULT_PLAYER_HEIGHT = 50;
	
	protected int laps;
	protected float speed;
	protected float xMove, yMove;
	boolean collision, passCheckpoint;
    private Rectangle grass;

    public Players(Handler handler, float x, float y, int width, int height, BufferedImage[] car) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		laps = 0;
		collision = false;
		
		// Velocity of the car (speed and direction)
		xMove = 0;
		yMove = 0;
		
		grass = new Rectangle(150, 200, 550, 300);
	}

	public void laps(){
		
		//Rectangle grass = new Rectangle(150, 200, 550, 300);
		Rectangle hitbox = new Rectangle(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); //player boundary
		//Rectangle outside = new Rectangle(50, 100, 750, 500);
		Rectangle finishline = new Rectangle( 700, 350, 800, 1 );
		Rectangle checkpoint = new Rectangle( 50, 350, 100, 1 ); //Ensure players don't cheat must cross checkpoint before finish line. This is how most racing games check
		
		/// LAPS
		if (hitbox.intersects(checkpoint)) {
			passCheckpoint = true;
		}
		
		if (hitbox.intersects(finishline) && passCheckpoint) { 
			System.out.println("Winner");
			laps++;
			passCheckpoint = false;
		}
			
	}
	
	// Comprehensive collision detection using a mix of mathematical and graphical collision
	// Graphical alone didn't work when the kart collided if a boundary backwards
	public void moveX() {
		
	    if (xMove > 0) { // Moving right
	    	// Contains checks whether or not this Rectangle entirely is contained the specified Rectangle.
	    	// Intersect determines whether or not this Rectangle and the specified Rectangle intersect.
			if (!grass.contains((int) (x + bounds.width), y + 5) && !grass.contains((x + bounds.width), bounds.height) && x - bounds.width < 710  ) {
				x += xMove;
				collision = false;
			} else {
				collision = true;
				Assets.sfx.get("Grass").play();
				setSpeed(-1);

			}
		}else if (xMove < 0) { // Moving left
						
			if (!grass.contains(x + 5, y + 5) && !grass.contains(x + 5, bounds.height) && x > 50 ) {
				x += xMove;
				collision = false;
			}else {
				collision = true;
				Assets.sfx.get("Grass").play();
				setSpeed(-1);

			}
		} 
		
	}
	
	public void moveY() {
		
		if (yMove < 0) { // Moving up
						
			if (!grass.contains(x + 5, y + 5) && !grass.contains(bounds.width, y + 5 ) && y > 100  ) {
				y += yMove;
				collision = false;
			}else {
				collision = true;
				Assets.sfx.get("Grass").play();
				setSpeed(-1);

			}
			
			
		} else if (yMove > 0) { // Moving down
			if (!grass.contains(x + 5, y + bounds.height + 5) && !grass.contains(bounds.width, y + bounds.height + 5) && y < 550 ) {
				y += yMove;
				collision = false;
			}else {
				collision = true;
				Assets.sfx.get("Grass").play();
				setSpeed(-1);
			}
		}
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void direction(int currentImageIndex) {
		
		// Player movement based on current angle
		if (currentImageIndex == 0 ) {
			yMove = yMove - 2 * speed;
		} else if (currentImageIndex == 1 ) {
			xMove = xMove + 1 * speed;
			yMove = yMove - 2 * speed;
		} else if (currentImageIndex == 2 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove - 2 * speed;
		} else if (currentImageIndex == 3 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove - 1 * speed;
		} else if (currentImageIndex == 4 ) {
			xMove = xMove + 2 * speed;
		}else if (currentImageIndex == 5 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove + 1 * speed;
		}else if (currentImageIndex == 6 ) {
			xMove = xMove + 2 * speed;
			yMove = yMove + 1 * speed;
		}else if (currentImageIndex == 7 ) {
			xMove = xMove + 1 * speed;
			yMove = yMove + 2 * speed;
		}else if (currentImageIndex == 8 ) {
			yMove = yMove + 2 * speed;
		}else if (currentImageIndex == 9 ) {
			xMove = xMove - 1 * speed;
			yMove = yMove + 2 * speed;
		}else if (currentImageIndex == 10 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove + 2 * speed;
		}else if (currentImageIndex == 11 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove + 1 * speed;
		}else if (currentImageIndex == 12 ) {
			xMove = xMove - 2 * speed;
		}else if (currentImageIndex == 13 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove - 1 * speed;
		}else if (currentImageIndex == 14 ) {
			xMove = xMove - 2 * speed;
			yMove = yMove - 2 * speed;
		}else if (currentImageIndex == 15 ) {
			xMove = xMove - 1 * speed;
			yMove = yMove - 2 * speed;
		}
	}
}
