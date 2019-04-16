import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Assets {
	
	private static final int width = 50, height = 50, rows = 4, cols = 4;
		
	//Array containing images for each angle of go-kart
	public static BufferedImage[] player1_move; //BufferImage more efficient then imageicon 
	public static BufferedImage[] player2_move;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(KartLoader.loadImage("/player1gokart/spritesheet.png"));
		SpriteSheet sheet2 = new SpriteSheet(KartLoader.loadImage("/player2gokart/spritesheet.png"));
		
		player1_move = new BufferedImage[16];
		player2_move = new BufferedImage[16];
		
		//loop through sprite
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				player1_move[(i*cols) + j] = sheet.crop(j*width, i*height, width, height);
				player2_move[(i*cols) + j] = sheet2.crop(j*width, i*height, width, height);
			}
		}
		
	}
}
