import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 50, height = 50;
	
	public static BufferedImage player1, player2;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(KartLoader.loadImage("/player1gokart/spritesheet.png"));
		
		player1 = sheet.crop(0, 0, width, height);
	}
}
