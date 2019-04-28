import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Players {
	
	//public static BufferedImage currentImage;
	private Animation rotation;
	protected int currentImageIndex = 0; //currentImageIndex2 = 0;

	private String username;
	//private BufferedImage[] car;
	//private InputHandler input;
	
	// Temporary speed variable
    private float xDelta = 0;
	
    String controls;
    	
    //public Player1(Handler handler, float x, float y) {
	public Player(Game game, float x, float y, BufferedImage[] car, String tempControls, String username) {
		super(game, x, y, Players.DEFAULT_PLAYER_WIDTH, Players.DEFAULT_PLAYER_HEIGHT, car );
		//bounds.x = 700;
		//bounds.y = 350;
		bounds.width = 40; // Hitbox of player is 5% smaller than the 50x50 kart image, This is account for the transparent background 
		bounds.height = 40;
		this.username = username;
		controls = tempControls;
		rotation = new Animation(car);
		//this.car = car;
		//Animation
		//rotation = new Animation(Assets.player1_move);
		
	}
	
	@Override
	public void tick() {
		//Movement
		
		getInput();
		laps();
		moveX();
		moveY();
		//System.out.println(this.getUsername()+this.x+this.y+this.currentImageIndex);
		//Packet02Move packet = new Packet02Move(this.getUsername(), this.x, this.y);
		Packet02Move packet = new Packet02Move(this.getUsername(), this.x, this.y, this.currentImageIndex);
		packet.writeData(game.socketClient);
	
	}
	
	//Update any variables for the object
	private void getInput() {
		xMove = 0;
		yMove = 0;
		//speed -= xDelta;
		
		//https://stackoverflow.com/questions/14824786/using-wasd-and-arrow-keys-simultaneously
//		if (controls == "wasd") {
//			
//			//Speed
//			if(game.getKeyManager().up) {		
//				setSpeed(1); //increase speed each time arrow press
//				speed = xDelta;
//			}
//			if(game.getKeyManager().down) {
//				setSpeed(-1); //decreases speed each time arrow press
//				speed = xDelta;
//			}
//			
//			direction(currentImageIndex);
//			
//		}
		
		//if (controls == "arrows") {
			
			//Speed
			if(game.getKeyManager().up2) {		
				setSpeed(1); 
				speed = xDelta;
			}
			if(game.getKeyManager().down2) {
				setSpeed(-1); 
				speed = xDelta;
			}
			
			//direction(currentImageIndex2);
			direction(currentImageIndex);
			
		//}
		
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
		
		//if (controls == "wasd") {
			g.drawImage(rotation.getCurrentFrame(currentImageIndex), (int)x, (int)y, width, height, null); //player
			//g.setColor(Color.red);
			//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		//}
		
//		if (controls == "arrows") {
//			g.drawImage(rotation.getCurrentFrame(currentImageIndex2), (int)x, (int)y, width, height, null); //player
//			//g.setColor(Color.red);
//			//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
//		}
		
		//g.setColor(Color.red);
		//g.fillRect(((int)(x + 5)), ((int)(y + 5)), bounds.width, bounds.height); // Hitbox
		//g.fillRect(50, 350, 100, 10 ); 
		
	}

	//Steering animation
	private void getCurrentAnimationFrame() {
		
		//if (controls == "wasd") {
			if(game.getKeyManager().left2) {
				if (currentImageIndex <= 0) {
					currentImageIndex = Assets.player1_move.length;
				}
				--currentImageIndex;
							
			} else if (game.getKeyManager().right2) {
				if (currentImageIndex >= Assets.player1_move.length - 1) {
					currentImageIndex = -1;
				}
				++currentImageIndex;
			}
		//}
		
//		if (controls == "arrows") {
//			if(game.getKeyManager().left2) {
//				if (currentImageIndex2 <= 0) {
//					currentImageIndex2 = Assets.player2_move.length;
//				}
//				--currentImageIndex2;
//							
//			} else if (game.getKeyManager().right2) {
//				if (currentImageIndex2 >= Assets.player2_move.length - 1) {
//					currentImageIndex2 = -1;
//				}
//				++currentImageIndex2;
//			}
//			
//		}
				
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
    
    public String getUsername() {
        return this.username;
    }

	public void setCurrentImageIndex(int currentImageIndex) {
		this.currentImageIndex = currentImageIndex;
	}

//	public void setCurrentImageIndex2(int currentImageIndex2) {
//		this.currentImageIndex2 = currentImageIndex2;
//	}


    
}
