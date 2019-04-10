import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KartLoader {

		public static BufferedImage loadImage(String path) {
			try {
				return ImageIO.read(KartLoader.class.getResource(path));
			} catch (IOException e) {
				
				e.printStackTrace();
				System.exit(1); //End game if image can't load
			}
			return null;
			
		}
}
