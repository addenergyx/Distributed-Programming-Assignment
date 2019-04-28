import java.awt.image.BufferedImage;
import java.net.InetAddress;

public class PlayerMP extends Player {

	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Game game, float x, float y, String username, BufferedImage[] car , String tempControls, InetAddress ipAddress, int port) {
		super(game, x, y, car, tempControls, username);
		
		this.ipAddress = ipAddress;
		this.port = port;
	}

	//For local player
	public PlayerMP(Game game, float x, float y, String username, BufferedImage[] car, InetAddress ipAddress, int port) {
		super(game, x, y, car, null, username);
		
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	
	@Override
	public void tick() {
		super.tick(); // Maintain same connection with player1
	}
}
