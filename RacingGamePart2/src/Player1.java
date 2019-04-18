import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player1 extends Players {
	
	public static BufferedImage currentImage;
	private Animation rotation;
	public static int currentImageIndex = 0;
	//private AudioPlayer sfx;

	// Temporary speed variable
    private float xDelta = 0;
    	
	public Player1(Handler handler, float x, float y) {
		super(handler, x, y, Players.DEFAULT_PLAYER_WIDTH, Players.DEFAULT_PLAYER_HEIGHT);
		bounds.x = 700;
		bounds.y = 350;
		bounds.width = 40;
		bounds.height = 40;
		
		//Animation
		rotation = new Animation(Assets.player1_move);
	}
	
	@Override
	public void tick() {
		//Movement
		getInput();
		laps();
		moveX();
		moveY();
	}
	
	//Update any variables for the object
	private void getInput() {
		xMove = 0;
		yMove = 0;
		//speed -= xDelta;
		
		//Speed
		if(handler.getKeyManager().up) {		
			setSpeed(1); //increasse speed each time arrow press
			//yMove =+ speed;
			//bounds.y = (int)yMove;
			speed = xDelta;
			//System.out.println(speed);
		}
		if(handler.getKeyManager().down) {
			setSpeed(-1); //decreases speed each time arrow press
			//yMove =- speed;
			//bounds.y = (int)yMove;
			speed = xDelta;
		}
			
		
//		if(handler.getKeyManager().right) {
//			speed -= xDelta;
//			
//			if (currentImageIndex == 3 ) {
//				xMove += 20;
//				//xMove = xMove + 2 * speed;
//				//yMove = yMove - 1 * speed;
//			} else if (currentImageIndex == 4 ) {
//				//xMove = xMove + 2 * speed;
//			} 
//			
//			//delay to make rotation slower, car easier to control, although animation looks less smooth now
//			try {
//				Thread.sleep(75); 
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		if(handler.getKeyManager().left) {
//			if (currentImageIndex == 12 ) {
//				xMove = xMove - 2 * speed;
//			}
//		}
		
		direction(currentImageIndex);
		
		
		//delay to make rotation slower, car easier to control, although animation looks less smooth now
		try {
			Thread.sleep(50); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Direction	
//		if(handler.getKeyManager().left)
//			if(currentImageIndex > 8) {
//				xMove = -speed;
//				bounds.x = (int)-speed;
//			}else if ( currentImageIndex < 8 && currentImageIndex > 0 ) {
//				xMove = speed;
//				bounds.x = (int)xMove;
//			}
//		if(handler.getKeyManager().right)
//			if(currentImageIndex < 8) {
//				xMove = speed;
//				bounds.x = (int)xMove;
//			}else if(currentImageIndex > 8) {
//				xMove = -speed;
//				bounds.x = (int)-speed;
//			}
			
	}

	@Override
	public void render(Graphics g) {
		
//		boolean up = false, down = false, left = false, right = false;
//		
//		if(handler.getKeyManager().up) {
//			g.drawImage(Assets.player1_move[2], 50, 50, width, height, null);
//			up = true;
//		} else if (handler.getKeyManager().down) {
//			g.drawImage(Assets.player_move[8], (int)x, (int)y, width, height, null);
//			down = true;
//		} else if (handler.getKeyManager().right) {
//			g.drawImage(Assets.player_move[4], (int)x, (int)y, width, height, null);
//			right = true;
//		} else if (handler.getKeyManager().left) {
//			g.drawImage(Assets.player_move[12], (int)x, (int)y, width, height, null);
//			left = true;
//		}
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
		
		
		g.drawImage(getCurrentAnimationFrame(), (int)x, (int)y, width, height, null); //player
				
		//g.setColor(Color.red);
		//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		//g.fillRect(50, 350, 100, 10 ); 
		
	}

	//Steering animation
	private BufferedImage getCurrentAnimationFrame() {
		if(handler.getKeyManager().left) {
			if (currentImageIndex <= 0) {
				currentImageIndex = Assets.player1_move.length;
			}
			--currentImageIndex;
						
		} else if (handler.getKeyManager().right) {
			if (currentImageIndex >= Assets.player1_move.length - 1) {
				currentImageIndex = -1;
			}
			++currentImageIndex;
		}
		return rotation.getCurrentFrame(currentImageIndex);
	}

    protected void setSpeed(int delta) {
        xDelta += delta;
        // Check the change in speed to ensure it's within the appropriate range
        if (xDelta < 0) {
            xDelta = 0;
        } else if (xDelta > 9) {
            xDelta = 9;
        }
        
//        if (collision) {
//        	xDelta = 0f; //reset speed on collision
//        }
        
    }
	

}
