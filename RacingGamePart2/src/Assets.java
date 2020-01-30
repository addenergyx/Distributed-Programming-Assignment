import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {
	
	private static final int width = 50, height = 50, rows = 4, cols = 4;
		
	//Array containing images for each angle of go-kart
	public static BufferedImage[] player1_move; //BufferImage more efficient than imageicon 
	public static BufferedImage[] player2_move;
	public static BufferedImage fire;
    public static HashMap<String, AudioPlayer> sfx;
    public static HashMap<String, MusicPlayer> music;
	
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
		
		fire = KartLoader.loadImage("/fire/fire.png");
		
		// The reason for two classes for handling sound is there is a vital
		// difference between how they should run.
		// Music must run continuously whereas sound effects should only play once per action
		
		// Car sound effects
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Grass", new AudioPlayer("./media/sounds/side_crash.wav"));
		sfx.put("Car", new AudioPlayer("./media/sounds/crash.wav"));
		sfx.put("Speed up", new AudioPlayer("./media/sounds/speedup.wav"));
		
		// Music
		music = new HashMap<String, MusicPlayer>();
		music.put("Menu", new MusicPlayer("Menu"));
		music.put("Stadium", new MusicPlayer("Stadium"));
	}
}
