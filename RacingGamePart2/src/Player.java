import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Players {
	
	public static BufferedImage currentImage;
	private Animation rotation;
	public static int currentImageIndex = 0, currentImageIndex2 = 0;

	// Temporary speed variable
    private float xDelta = 0;
	
    String controls;
    	
    //public Player1(Handler handler, float x, float y) {
	public Player(Handler handler, float x, float y, BufferedImage[] car, String tempControls) {
		super(handler, x, y, Players.DEFAULT_PLAYER_WIDTH, Players.DEFAULT_PLAYER_HEIGHT, car );
		//bounds.x = 700;
		//bounds.y = 350;
		bounds.width = 40; // Hitbox of player is 5% smaller than the 50x50 kart image, This is account for the transparent background 
		bounds.height = 40;
		
		controls = tempControls;
		
		//Animation
		//rotation = new Animation(Assets.player1_move);
		rotation = new Animation(car);
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
		
		//https://stackoverflow.com/questions/14824786/using-wasd-and-arrow-keys-simultaneously
		if (controls == "wasd") {
			
			//Speed
			if(handler.getKeyManager().up) {		
				setSpeed(1); //increase speed each time arrow press
				speed = xDelta;
			}
			if(handler.getKeyManager().down) {
				setSpeed(-1); //decreases speed each time arrow press
				speed = xDelta;
			}
			
			direction(currentImageIndex);
			
		}
		
		if (controls == "arrows") {
			
			//Speed
			if(handler.getKeyManager().up2) {		
				setSpeed(1); 
				speed = xDelta;
			}
			if(handler.getKeyManager().down2) {
				setSpeed(-1); 
				speed = xDelta;
			}
			
			direction(currentImageIndex2);
			
		}
		
		getCurrentAnimationFrame();
		
		//delay to make rotation slower, car easier to control, although animation looks less smooth now
		try {
			Thread.sleep(40); //Tried many different times and found 40 to be the best for easy controls and good fps
			// Below 25 made the cars hard to control and above 50 had terrible frames per second 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void render(Graphics g) {
		
		if (controls == "wasd") {
			g.drawImage(rotation.getCurrentFrame(currentImageIndex), (int)x, (int)y, width, height, null); //player
			//g.setColor(Color.red);
			//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		}
		
		if (controls == "arrows") {
			g.drawImage(rotation.getCurrentFrame(currentImageIndex2), (int)x, (int)y, width, height, null); //player
			//g.setColor(Color.red);
			//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		}
		
		//g.setColor(Color.red);
		//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		//g.fillRect(50, 350, 100, 10 ); 
		
	}

	//Steering animation
	private void getCurrentAnimationFrame() {
		
		if (controls == "wasd") {
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
		}
		
		if (controls == "arrows") {
			if(handler.getKeyManager().left2) {
				if (currentImageIndex2 <= 0) {
					currentImageIndex2 = Assets.player2_move.length;
				}
				--currentImageIndex2;
							
			} else if (handler.getKeyManager().right2) {
				if (currentImageIndex2 >= Assets.player2_move.length - 1) {
					currentImageIndex2 = -1;
				}
				++currentImageIndex2;
			}
			
		}
				
	}

    protected void setSpeed(int delta) {
        xDelta += delta;
        // Check the change in speed to ensure it's within the appropriate range
        if (xDelta < 0) {
            xDelta = 0;
        } else if (xDelta > 9) {
            xDelta = 9;
        }
        
    }
    
    public Rectangle getBounds() {
		return bounds;
    	
    }
    
}
