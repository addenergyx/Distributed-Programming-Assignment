import java.awt.image.BufferedImage;

public class Animation {

	private int index;
	private BufferedImage[] frames;
	
	public Animation(BufferedImage[] frames) {
		this.frames = frames;
		index = 0; //first image straight on start line
	}
		
	
	public BufferedImage getCurrentFrame(int index) {
		return frames[index];
	}
	
}
