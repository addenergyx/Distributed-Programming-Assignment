import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class MenuState extends State {
	
		private Rectangle playButton = new Rectangle(handler.getGame().width / 2 - 50, 200, 100, 50);  
		private Rectangle helpButton = new Rectangle(handler.getGame().width / 2 - 50, 350, 100, 50);  
		private Rectangle quitButton = new Rectangle(handler.getGame().width / 2 - 50, 500, 100, 50); 
		private int x = 0;
		
	public MenuState(Handler handler){
		super(handler);
		this.handler = handler;
		Assets.music.get("Menu").run();
		
	}

	@Override
	public void tick() {
		
		if(handler.getMouseManager().play) {
			Assets.music.get("Menu").close();
			Assets.music.get("Stadium").run();
			State.setState(handler.getGame().getGameState());
		} else if (handler.getMouseManager().quit) {
			System.exit(0);
		} else if (handler.getMouseManager().help) {
			
			String message = "A car can be controlled with 4 keys:" +
                    "\n" +
                    "1. Accelerate (Key: up arrow / W): \n" +
                    "2. Break (Key: down arrow / D)\n\n" +
                    "3. Turn left (Key: left arrow / A)\n" +
                    "    - Turns the car’s 22.5 degrees counter-clockwise.\n" +
                    "\n" +
                    "4. Turn right (Key: right arrow / S)\n" +
                    "    - Turns the car’s 22.5 degrees clockwise.\n"
                    + "\n"
                    + "Collision Rules\n"
                    + "\n"
                    + "Cars that hit the grass will sloww down and reverse.\n"
                    + "When two cars hit eachother the game will end.";
			
			// Using separate thread to main thread so it doesn't break the flow of moving background menu
			// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
			Thread t = new Thread(new Runnable(){
			        public void run(){
			            JOptionPane.showMessageDialog(null, message, "Help", 1);
			        }
			    });
			t.start();

			 handler.getMouseManager().help = false;
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g.create();
		
		g.setFont(new Font("arial", Font.BOLD, 50));
		g.drawString("Racing Game", 260, 100);
		
		g.setFont(new Font("arial", Font.BOLD, 30));
		g2d.draw(playButton);
		g.drawString("Play", playButton.x + 22, playButton.y + 32);
		g2d.draw(helpButton);		
		g.drawString("Help", helpButton.x + 22, helpButton.y + 32);
		g2d.draw(quitButton);
		g.drawString("Quit", quitButton.x + 22, quitButton.y + 32);

		// Moving car in menu
		if (x <= 750) {
			g.drawImage(Assets.player2_move[4], x, 420,null);
			x++;
		} else if (x > 750) {
			g.drawImage(Assets.player1_move[4], x, 300,null);
			x--;
		}
		
		// Blur effect under title
		for (int i = 850; i > 100; i--) {
			g.drawImage(Assets.player1_move[12], i, 110,null);
		}
		

	}
	
}