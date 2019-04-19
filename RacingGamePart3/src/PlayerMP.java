import java.net.InetAddress;

public class PlayerMP extends Player1 {

	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Handler handler, float x, float y, InetAddress ipAddress, int port) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
		this.ipAddress = ipAddress;
		this.port = port;
	}

	

	
	@Override
	public void tick() {
		super.tick(); //Maintain same connection with player1
	}
}
