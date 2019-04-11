import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Assets {
	
	private static final int width = 50, height = 50, rows = 4, cols = 4;
	
	//public static BufferedImage player1, player1TopLeft;
	
	//Array containing images for each angle of go-kart
	public static BufferedImage[] player_move; //BufferImage more efficient then imageicon 
		
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(KartLoader.loadImage("/player1gokart/spritesheet.png"));
		
		player_move = new BufferedImage[16];
		
		//loop through sprite
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				player_move[(i*cols) + j] = sheet.crop(j*width, i*height, width, height);
			}
		}
		
//		player_move[0] = sheet.crop(0, 0, width, height);
//		player_move[1] = sheet.crop(width*2, height*2, width, height);
//		player_move[2] = sheet.crop(width*2, height*3, width, height);
//		player_move[3] = sheet.crop(0, height, width, height);
		
//		player1 = sheet.crop(0, 0, width, height);
	}
}
